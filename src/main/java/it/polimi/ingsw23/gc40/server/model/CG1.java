package it.polimi.ingsw23.gc40.server.model;

public class CG1 implements Goal{
    int grandezzaBlocco;
    int numeroBlocchi;
    @Override
    public boolean instanceGoal(Player p) {
        int n = 0;
        ItemTile right = p.getShelf().getItemTile(0,0).rightTile(p.getShelf());
        ItemTile below = p.getShelf().getItemTile(0,0).belowTile(p.getShelf());
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++){
                if (p.getShelf().getItemTile(i ,j ).equals(right))
                    n++;
                else if(p.getShelf().getItemTile(i ,j ).equals(below))
                    n++;
                right = p.getShelf().getItemTile(i,j).rightTile(p.getShelf());
                below = p.getShelf().getItemTile(i,j).belowTile(p.getShelf());
                n++;
            }

        return (n > numeroBlocchi);
        //TO DO
    }
}
