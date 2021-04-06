package com.example.unusualchess.view;

import android.app.Activity;
import android.util.Log;

import com.example.unusualchess.R;
import com.example.unusualchess.board.BoardHolder;
import com.example.unusualchess.board.CellIndex;
import com.example.unusualchess.board.ChessModel;
import com.example.unusualchess.board.Piece;
import com.example.unusualchess.common.Role;
import com.example.unusualchess.util.ChessModelListener;
import com.example.unusualchess.util.ChessMoveEvent;
import com.mfratane.boardview.BoardView;

import java.util.EnumMap;

/**
 * This is a helper class to handle all UI logic for ChessBattleActivity
 */
public class ChessBattleUI implements ChessModelListener {
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

    public ChessBattleUI(Activity parent, ChessModel model) {
        _view = parent.findViewById(R.id.battle_menu_board);
        model.addListener(this);

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

                if(p != null) {
                    int spriteId = getSpriteId(p);
                    _view.setPiece(ChessModel.BOARD_WIDTH - 1 - file, rank, spriteId);
                } else {
                    try {
                        _view.removePiece(file, rank);
                    } catch (NullPointerException e) {
                        Log.d(TAG, "updateUI: nothing to remove in pos: " +
                                new CellIndex(file, rank));
                    }
                }
            }
        }
    }

    @Override
    public void onMove(ChessMoveEvent ev) {
        //TODO: Implement move sequence number check
        BoardView.Pos srcPos = fromCellIndex(ev.getSrc());
        BoardView.Pos dstPos = fromCellIndex(ev.getDst());
        _view.movePiece(srcPos, dstPos);
    }

    private final BoardView _view;
}
