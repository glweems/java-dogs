package com.lambdaschool.bunnies;

import com.lambdaschool.bunnies.model.Bunny;

import java.util.ArrayList;

public class BunnyList
{
    public ArrayList<Bunny> bunnyList = new ArrayList<Bunny>();

    public BunnyList()
    {
        bunnyList.add(new Bunny("BarnBarn", 5.1));
        bunnyList.add(new Bunny("Cinnamon", 4.3));
        bunnyList.add(new Bunny("Jessica", 4.7));
    }

    public Bunny findBunny (CheckBunny tester)
    {
        for (Bunny b:bunnyList)
        {
            if (tester.test(b))
            {
                return b;
            }
        }
        return null;
    }
}
Collapse



