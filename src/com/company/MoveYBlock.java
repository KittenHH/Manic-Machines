package com.company;

public class MoveYBlock extends ColisionCheck {
    MoveYBlock() {}
    public Block moveYBlock (Block block) {
        block.Y = Move(block);
        block.ExtraData[2] = ReverseMovement(block);
        return block;
    }
    public int Move(Block block) {
        block.Y = block.Y + block.ExtraData[2];
        return block.Y;
    }
    public int ReverseMovement(Block block) {
        if(block.Y > block.ExtraData[1] || block.ExtraData[0] > block.Y)
        {block.ExtraData[2] = block.ExtraData[2] - block.ExtraData[2]*2;}

        return block.ExtraData[2];
    }
    //this will dose special collision player check, so the player Y is stuck to the block
    public void setPlayerYCheck(Block block) {
        if(Player.IsJumping == false || block.ExtraData[2] < 0) {return;}

    }
    //code for block that moves across X axis
    public Block moveXBlock(Block block) {
        block.X = MoveX(block);
        block.ExtraData[2] = ReverseMovement(block);
        return block;
    }
    public int MoveX(Block block) {
        block.X = block.X + block.ExtraData[2];
        return block.X;
    }

    public int ReverseMovementX(Block block) {
        if(block.X > block.ExtraData[1] || block.ExtraData[0] > block.X)
        {block.ExtraData[2] = block.ExtraData[2] - block.ExtraData[2]*2;}
        return block.ExtraData[2];
    }
    //moves any obj touching the block
    public void ForceX(Block b) {
        //first for the player
        if(Player.IsJumping == false &&
        Check(b.X,b.Y,b.Width,b.Height,Player.X,Player.Y,Player.width,Player.height) &&
        Player.HasBeenMoved == false &&
        Player.HasBeenXStop == false) {
            Player.X += b.ExtraData[2];
          Player.HasBeenMoved = true;
        }
        //next for enemies
        for(int i = 0;i < EnemyHandeler.EnemyList.length;i++) {
            EnemyHandeler.Enemy c = EnemyHandeler.EnemyList[i];
            if(c == null || c.Type == "Bat" || c.Type == "Bat2" || c.isJumping == true || c.HasBeenMoved == true) {
                continue;}
            if(Check(b.X,b.Y,b.Width,b.Height, c.X,c.Y,c.Width,c.Height)) {
                c.X += b.ExtraData[2];
                c.HasBeenMoved = true;
                EnemyHandeler.EnemyList[i] = c;
            }
        }
    }
}
