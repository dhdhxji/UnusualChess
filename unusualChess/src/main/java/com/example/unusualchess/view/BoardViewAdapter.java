package com.example.unusualchess.view;

import android.app.Activity;
import android.util.Log;

import com.example.unusualchess.R;
import com.example.unusualchess.chessModel.board.BoardHolder;
import com.example.unusualchess.chessModel.board.CellIndex;
import com.example.unusualchess.chessModel.ChessModel;
import com.example.unusualchess.chessModel.board.Piece;
import com.example.unusualchess.chessModel.common.Role;
import com.example.unusualchess.util.BoardListener;
import com.example.unusualchess.chessModel.common.ChessModelListener;
import com.example.unusualchess.chessModel.common.ChessMoveEvent;
import com.mfratane.boardview.BoardView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

/**
 * This is a helper class to handle all UI logic for ChessBattleActivity
 */
public class BoardViewAdapter implements ChessModelListener<Piece>, BoardView.BoardListener {
    public static final String TAG = "ChessBattleUI";

    public static final EnumMap<Role, EnumMap<Piece.Type, Integer>> pieceSprites = genSpriteTable();

    /**
     * Generate sprite conversion table to convert piece role-type pair into sprite id
     * @return sprite conversion table
     */
    private static EnumMap<Role, EnumMap<Piece.Type, Integer>> genSpriteTable() {
        EnumMap<Piece.Type, Integer> whitePieces =
                new EnumMap<>(Piece.Type.class);
        whitePieces.put(Piece.Type.BISHOP, R.drawable.p_wh_bishop);
        whitePieces.put(Piece.Type.KING, R.drawable.p_wh_king);
        whitePieces.put(Piece.Type.KNIGHT, R.drawable.p_wh_knight);
        whitePieces.put(Piece.Type.PAWN, R.drawable.p_wh_pawn);
        whitePieces.put(Piece.Type.QUEEN, R.drawable.p_wh_queen);
        whitePieces.put(Piece.Type.ROOK, R.drawable.p_wh_rook);

        EnumMap<Piece.Type, Integer> blackPieces =
                new EnumMap<>(Piece.Type.class);
        blackPieces.put(Piece.Type.BISHOP, R.drawable.p_bl_bishop);
        blackPieces.put(Piece.Type.KING, R.drawable.p_bl_king);
        blackPieces.put(Piece.Type.KNIGHT, R.drawable.p_bl_knight);
        blackPieces.put(Piece.Type.PAWN, R.drawable.p_bl_pawn);
        blackPieces.put(Piece.Type.QUEEN, R.drawable.p_bl_queen);
        blackPieces.put(Piece.Type.ROOK, R.drawable.p_bl_rook);

        EnumMap<Role, EnumMap<Piece.Type, Integer>> res = new EnumMap<>(Role.class);

        res.put(Role.BLACK, blackPieces);
        res.put(Role.WHITE, whitePieces);

        return res;
    }

    @SuppressWarnings("ConstantConditions")
    public static int getSpriteId(Piece p) {
        return pieceSprites.get(p.getRole()).get(p.getType());
    }

    public static BoardView.Pos fromCellIndex(CellIndex i) {
        return new BoardView.Pos(ChessModel.BOARD_WIDTH - 1 - i.getRank(), i.getFile());
    }

    public static CellIndex fromPos(BoardView.Pos p) {
        return new CellIndex( p.getJ(), ChessModel.BOARD_WIDTH - 1 - p.getI());
    }

    public BoardViewAdapter(Activity parent, ChessModel model, BoardListener l) {
        _view = parent.findViewById(R.id.battle_menu_board);
        model.addListener(this);

        _clickListener = l;
        _view.setBoardListener(this);


        updateUI(model.getCurrentState());
    }

    /**
     * Update UI based on board state
     * @param board board state that holds all piece locations
     */
    public void updateUI(BoardHolder<Piece> board) {
        for(int rank = 0; rank < ChessModel.BOARD_WIDTH; ++rank) {
            for(int file = 0; file < ChessModel.BOARD_WIDTH; ++file) {
                Piece p = board.get(new CellIndex(file, rank));
                BoardView.Pos pos = fromCellIndex(new CellIndex(file, rank));

                if(p != null) {
                    int spriteId = getSpriteId(p);
                    _view.setPiece(
                            pos.getI(),
                            pos.getJ()
                            , spriteId);
                } else {
                    try {
                        _view.removePiece(
                                pos.getI(),
                                pos.getJ()
                        );
                    } catch (NullPointerException e) {
                        Log.d(TAG, "updateUI: nothing to remove in pos: " +
                                new CellIndex(file, rank));
                    }
                }
            }
        }
    }

    /**
     * Highlight available moves
     * @param pos set of positions to highlight
     */
    public void highlightTiles(Set<CellIndex> pos) {
        List<BoardView.Pos> iPos = new ArrayList<>();

        for(CellIndex i: pos) {
            iPos.add(fromCellIndex(i));
        }

        _view.markTiles(iPos);
    }

    public void unmarkTiles() {
        _view.unmarkAllTiles();
    }

    /**
     * This callback will be called on every chess model update
     * @param ev update events
     */
    @Override
    public void onMove(ChessMoveEvent<Piece> ev) {
        Log.d(TAG, "onMove: move from " + ev.getSrc() + " to " + ev.getDst());
        BoardView.Pos srcPos = fromCellIndex(ev.getSrc());
        BoardView.Pos dstPos = fromCellIndex(ev.getDst());

        _view.movePiece(srcPos, dstPos);

        //Process transformation
        if(ev.getTransformTo() != null && ev.getTransformFrom() != ev.getTransformTo()) {

            _view.removePiece(dstPos.getI(), dstPos.getJ());
            _view.setPiece(dstPos.getI(), dstPos.getJ(), getSpriteId(ev.getTransformTo()));
        }

        Log.d(TAG, "onMove: BoardView src: " + srcPos + " BoardView dst: " + dstPos);

    }

    /**
     * This callback will be called on every single piece click on bard view
     * @param pos click position
     * @param isSameLast is prev click position is same as pos
     */
    @Override
    public void onClickPiece(BoardView.Pos pos, boolean isSameLast) {
        _clickListener.onBoardClick(fromPos(pos));
    }

    /**
     * This callback will be called when user move piece
     * @param posPiece
     * @param posTile
     */
    @Override
    public void onClickTile(BoardView.Pos posPiece, BoardView.Pos posTile) {
        _clickListener.onBoardClick(fromPos(posTile));
    }

    private BoardView.Pos _selectedPiece = null;
    private final BoardView _view;
    private BoardListener _clickListener;

}
