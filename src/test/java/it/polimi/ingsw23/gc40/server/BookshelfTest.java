package it.polimi.ingsw23.gc40.server;

import junit.framework.TestCase;

public class BookshelfTest extends TestCase {

    public void testInsertIT() {
        Bookshelf bookshelf = new Bookshelf();
        ItemTile book = new ItemTile(Item.BOOK);
        ItemTile cat = new ItemTile(Item.CAT);

        bookshelf.insertIT(book, 0);
        bookshelf.insertIT(book,0);
        bookshelf.insertIT(cat,0);
        bookshelf.insertIT(cat,1);

        assertEquals(bookshelf.getItemTile(5,0).getItem(), Item.BOOK );
        assertEquals(bookshelf.getItemTile(4,0).getItem(), Item.BOOK );
        assertEquals(bookshelf.getItemTile(3,0).getItem(), Item.CAT );
        assertEquals(bookshelf.getItemTile(5,1).getItem(), Item.CAT );
        assertEquals(bookshelf.getItemTile(2,0).getItem(), Item.EMPTY );
    }


    public void testPlaceables() {
        Bookshelf bookshelf = new Bookshelf();
        ItemTile book = new ItemTile(Item.BOOK);
        //when bookshelf is initialized, all ItemTile are EMPTY, so placeables should return 3
        assertEquals(bookshelf.placeables(), 3);

        //filling the lowest three lines, placeables still should return 3
        for(int i=5; i>=3; i--){
            for(int j=0; j<=4; j++){
                bookshelf.insertIT(book,j);
            }
        }
        assertEquals(bookshelf.placeables(), 3);

        //adding one tile in one column do not change the return of placeable
        bookshelf.insertIT(book,0);
        assertEquals(bookshelf.placeables(), 3);

        //adding another line, so placeable should return 2
        for(int j=1; j<=4; j++){
            bookshelf.insertIT(book,j);
        }
        assertEquals(bookshelf.placeables(), 2);

        //adding only less than 5 ItemTile in the same line placeable still should return 2
        for(int j=0; j<=3; j++){
            bookshelf.insertIT(book,j);
        }
        assertEquals(bookshelf.placeables(), 2);

        //adding the last ItemTile in the line placeables should return 1
        bookshelf.insertIT(book,4);
        assertEquals(bookshelf.placeables(), 1);

        //verify the same thing adding last line of ItemTile
        for(int j=0; j<=4; j++){
            bookshelf.insertIT(book,j);
            if(j<=3) {
                assertEquals(bookshelf.placeables(), 1);
                System.out.println("column"+ j +"ok");
            }
            else{
                assertEquals(bookshelf.placeables(), 0);
            }
        }
    }

    public void testGetAvailableSlots() {
        Bookshelf bookshelf = new Bookshelf();
        ItemTile book = new ItemTile(Item.BOOK);

        //first return must be 3, because bookshelf is created with all ItemTiles set to EMPTY and getAvailableSlots count up to three
        assertEquals(bookshelf.getAvailableSlots(0), 3);

        //putting four lines of ItemTiles in column not equal to n, the return should be the same, while for all other column, return should be 2
        for(int i=5; i>=2;i--) {
            for(int j=1; j<=4; j++) {
                bookshelf.insertIT(book, j);
            }
        }
        assertEquals(bookshelf.getAvailableSlots(1), 2);
        assertEquals(bookshelf.getAvailableSlots(2), 2);
        assertEquals(bookshelf.getAvailableSlots(3), 2);
        assertEquals(bookshelf.getAvailableSlots(4), 2);
        assertEquals(bookshelf.getAvailableSlots(0), 3);

        //adding one ItemTile in column 2 and two ItemTiles in column 3, return should be respectively 1 and 0
        bookshelf.insertIT(book, 2);
        bookshelf.insertIT(book, 3);
        bookshelf.insertIT(book, 3);
        assertEquals(bookshelf.getAvailableSlots(2), 1);
        assertEquals(bookshelf.getAvailableSlots(3), 0);

    }

    public void testChecker() {

            Bookshelf bookshelf = new Bookshelf();

            ItemTile book0 = new ItemTile(Item.BOOK);
            ItemTile book1 = new ItemTile(Item.BOOK);
            ItemTile book2 = new ItemTile(Item.BOOK);
            ItemTile book3 = new ItemTile(Item.BOOK);
            ItemTile book4 = new ItemTile(Item.BOOK);

            ItemTile cat0 = new ItemTile(Item.CAT);
            ItemTile cat1 = new ItemTile(Item.CAT);
            ItemTile cat2 = new ItemTile(Item.CAT);
            ItemTile cat3 = new ItemTile(Item.CAT);
            ItemTile cat4 = new ItemTile(Item.CAT);

            ItemTile plant0 = new ItemTile(Item.PLANT);
            ItemTile plant1 = new ItemTile(Item.PLANT);
            ItemTile plant2 = new ItemTile(Item.PLANT);
            ItemTile plant3 = new ItemTile(Item.PLANT);
            ItemTile plant4 = new ItemTile(Item.PLANT);

            ItemTile frame0 = new ItemTile(Item.FRAME);
            ItemTile frame1 = new ItemTile(Item.FRAME);
            ItemTile frame2 = new ItemTile(Item.FRAME);
            ItemTile frame3 = new ItemTile(Item.FRAME);
            ItemTile frame4 = new ItemTile(Item.FRAME);

            ItemTile game0 = new ItemTile(Item.GAME);
            ItemTile game1 = new ItemTile(Item.GAME);
            ItemTile game2 = new ItemTile(Item.GAME);
            ItemTile game3 = new ItemTile(Item.GAME);
            ItemTile game4 = new ItemTile(Item.GAME);

            ItemTile trophy0 = new ItemTile(Item.TROPHY);
            ItemTile trophy1 = new ItemTile(Item.TROPHY);
            ItemTile trophy2 = new ItemTile(Item.TROPHY);
            ItemTile trophy3 = new ItemTile(Item.TROPHY);
            ItemTile trophy4 = new ItemTile(Item.TROPHY);

            //first check, put one different type of ItemTile for each line
            bookshelf.insertIT(trophy0, 0);
            bookshelf.insertIT(trophy1, 1);
            bookshelf.insertIT(trophy2, 2);
            bookshelf.insertIT(trophy3, 3);
            bookshelf.insertIT(trophy4, 4);

            bookshelf.insertIT(game0, 0);
            bookshelf.insertIT(game1, 1);
            bookshelf.insertIT(game2, 2);
            bookshelf.insertIT(game3, 3);
            bookshelf.insertIT(game4, 4);

            bookshelf.insertIT(plant0, 0);
            bookshelf.insertIT(plant1, 1);
            bookshelf.insertIT(plant2, 2);
            bookshelf.insertIT(plant3, 3);
            bookshelf.insertIT(plant4, 4);

            bookshelf.insertIT(frame0, 0);
            bookshelf.insertIT(frame1, 1);
            bookshelf.insertIT(frame2, 2);
            bookshelf.insertIT(frame3, 3);
            bookshelf.insertIT(frame4, 4);

            bookshelf.insertIT(cat0, 0);
            bookshelf.insertIT(cat1, 1);
            bookshelf.insertIT(cat2, 2);
            bookshelf.insertIT(cat3, 3);
            bookshelf.insertIT(cat4, 4);

            bookshelf.insertIT(book0, 0);
            bookshelf.insertIT(book1, 1);
            bookshelf.insertIT(book2, 2);
            bookshelf.insertIT(book3, 3);
            bookshelf.insertIT(book4, 4);


        //before and after checking the right type of ItemTile, ADJScore should be zero because the ItemTile is not of the correct type
        bookshelf.checker(0, 0, Item.CAT);
        assertEquals(bookshelf.getADJCounter(), 0);

        bookshelf.checker(0,0, Item.BOOK);
        assertEquals(bookshelf.getADJCounter(), 5);
        //resetting ADJcounter before doing other tests, during the game another the other method who call checker does this
        bookshelf.setADJCounter(0);
        bookshelf.checker(0, 0, Item.CAT);
        assertEquals(bookshelf.getADJCounter(), 0);

        bookshelf.checker(1, 0, Item.CAT);
        assertEquals(bookshelf.getADJCounter(), 5);
        bookshelf.setADJCounter(0);

        bookshelf.checker(2, 0, Item.FRAME);
        assertEquals(bookshelf.getADJCounter(), 5);
        bookshelf.setADJCounter(0);

        bookshelf.checker(3, 0, Item.PLANT);
        assertEquals(bookshelf.getADJCounter(), 5);
        bookshelf.setADJCounter(0);

        bookshelf.checker(4, 0, Item.GAME);
        assertEquals(bookshelf.getADJCounter(), 5);
        bookshelf.setADJCounter(0);

        bookshelf.checker(5, 0, Item.TROPHY);
        assertEquals(bookshelf.getADJCounter(), 5);
        bookshelf.setADJCounter(0);

        //other corner case would be done in testADJScore that call this method
    }


    public void testADJScoreTrue() {
        //testing some corner case, verifying branch with 1/2/3/4/6+ state, the grid is full
        Bookshelf bookshelf2 = new Bookshelf();
        ItemTile plantn0 = new ItemTile(Item.PLANT);
        ItemTile plantn1 = new ItemTile(Item.PLANT);
        ItemTile plantn2 = new ItemTile(Item.PLANT);
        ItemTile plantn3 = new ItemTile(Item.PLANT);
        ItemTile plantn4 = new ItemTile(Item.PLANT);
        ItemTile plantn5 = new ItemTile(Item.PLANT);
        ItemTile plantn6 = new ItemTile(Item.PLANT);
        ItemTile plantn7 = new ItemTile(Item.PLANT);
        ItemTile plantn8 = new ItemTile(Item.PLANT);
        ItemTile plantn9 = new ItemTile(Item.PLANT);
        ItemTile plantn10 = new ItemTile(Item.PLANT);
        ItemTile plantn11 = new ItemTile(Item.PLANT);
        ItemTile plantn12 = new ItemTile(Item.PLANT);
        ItemTile plantn13 = new ItemTile(Item.PLANT);
        ItemTile plantn14 = new ItemTile(Item.PLANT);
        ItemTile bookn0 = new ItemTile(Item.BOOK);
        ItemTile bookn1 = new ItemTile(Item.BOOK);
        ItemTile bookn2 = new ItemTile(Item.BOOK);
        ItemTile bookn3 = new ItemTile(Item.BOOK);
        ItemTile bookn4 = new ItemTile(Item.BOOK);
        ItemTile bookn5 = new ItemTile(Item.BOOK);
        ItemTile catn0 = new ItemTile(Item.CAT);
        ItemTile catn1 = new ItemTile(Item.CAT);
        ItemTile catn2 = new ItemTile(Item.CAT);
        ItemTile catn3 = new ItemTile(Item.CAT);
        ItemTile trophiesn0 = new ItemTile(Item.TROPHY);
        ItemTile trophiesn1 = new ItemTile(Item.TROPHY);
        ItemTile trophiesn2 = new ItemTile(Item.TROPHY);
        ItemTile trophiesn3 = new ItemTile(Item.TROPHY);
        ItemTile trophiesn4 = new ItemTile(Item.TROPHY);

        bookshelf2.insertIT(plantn0, 0);
        bookshelf2.insertIT(plantn1, 0);
        bookshelf2.insertIT(plantn2, 0);
        bookshelf2.insertIT(trophiesn0, 0);
        bookshelf2.insertIT(catn0, 0);
        bookshelf2.insertIT(bookn0, 0);

        bookshelf2.insertIT(plantn3,1);
        bookshelf2.insertIT(trophiesn1,1);
        bookshelf2.insertIT(plantn4,1);
        bookshelf2.insertIT(plantn5,1);
        bookshelf2.insertIT(trophiesn2,1);
        bookshelf2.insertIT(bookn1,1);

        bookshelf2.insertIT(plantn6,2);
        bookshelf2.insertIT(plantn7,2);
        bookshelf2.insertIT(bookn2,2);
        bookshelf2.insertIT(bookn3,2);
        bookshelf2.insertIT(bookn4,2);
        bookshelf2.insertIT(trophiesn3,2);

        bookshelf2.insertIT(plantn8,3);
        bookshelf2.insertIT(trophiesn4,3);
        bookshelf2.insertIT(plantn9,3);
        bookshelf2.insertIT(plantn10,3);
        bookshelf2.insertIT(bookn5,3);
        bookshelf2.insertIT(catn1,3);

        bookshelf2.insertIT(plantn11,4);
        bookshelf2.insertIT(plantn12,4);
        bookshelf2.insertIT(plantn13,4);
        bookshelf2.insertIT(plantn14,4);
        bookshelf2.insertIT(catn2,4);
        bookshelf2.insertIT(catn3,4);


        bookshelf2.print();
        System.out.println();
        assertEquals(bookshelf2.ADJScore(), 13);
    }

    public void testADJScoreTrue2(){
        Bookshelf bookshelf3 = new Bookshelf();
        //testing some corner case, verifying branch with 5 state, the grid isn't full


        bookshelf3.insertIT(new ItemTile(Item.PLANT), 0);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 0);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 0);
        bookshelf3.insertIT(new ItemTile(Item.TROPHY), 0);
        bookshelf3.insertIT(new ItemTile(Item.CAT), 0);

        bookshelf3.insertIT(new ItemTile(Item.TROPHY), 1);
        bookshelf3.insertIT(new ItemTile(Item.TROPHY), 1);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 1);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 1);
        bookshelf3.insertIT(new ItemTile(Item.TROPHY), 1);

        bookshelf3.insertIT(new ItemTile(Item.TROPHY), 2);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 2);
        bookshelf3.insertIT(new ItemTile(Item.BOOK), 2);
        bookshelf3.insertIT(new ItemTile(Item.BOOK), 2);
        bookshelf3.insertIT(new ItemTile(Item.BOOK), 2);
        bookshelf3.insertIT(new ItemTile(Item.TROPHY), 2);

        bookshelf3.insertIT(new ItemTile(Item.PLANT), 3);
        bookshelf3.insertIT(new ItemTile(Item.TROPHY), 3);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 3);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 3);
        bookshelf3.insertIT(new ItemTile(Item.BOOK), 3);

        bookshelf3.insertIT(new ItemTile(Item.PLANT), 4);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 4);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 4);
        bookshelf3.insertIT(new ItemTile(Item.PLANT), 4);


        bookshelf3.print();
        assertEquals(bookshelf3.ADJScore(), 18);
    }
}