package m_prime;

import manager.SortingManager;
import manager.Trim;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;


public class PanelList extends JFrame
{

    private JCheckBox box1 = new JCheckBox( "Number of Contributor Followers" );
    private JCheckBox box2 = new JCheckBox( "Number of Forks" );
    private JCheckBox box3 = new JCheckBox( "Number of Releases" );

    public PanelList()
    {

        JPanel panelUp = new JPanel();
        panelUp.add( new JLabel( "<html><font color='white'>These are the results</font></html>" ) );
        this.add( panelUp, BorderLayout.NORTH );
        panelUp.setBackground( Color.GRAY );
        //this.setSize


        JPanel panelLeft = new JPanel();
        panelLeft.setBackground( Color.LIGHT_GRAY );
        JButton button = new JButton( "<html><font color='black'>Sort</font></html>" );
        button.setBackground( Color.white );

        panelLeft.add( button );
        this.add( panelLeft, BorderLayout.EAST );

        /**
         * name of the root file where the folders are
         */

        File sit = new File( "git" );

        String[] dir = sit.list();
        Vector< String > onlydir = new Vector<>();
        for ( String s :
                dir )
        {
            if ( new File( sit.getAbsolutePath() + File.separator + File.separator + s ).isDirectory() )
            {
                onlydir.add( s );
            }

        }

        AtomicReference< FileTree > model = new AtomicReference<>();


        model.set( new FileTree( sit, onlydir ) );
        this.add( model.get(), BorderLayout.CENTER );
        button.addActionListener( e ->
        {

            // Get new tree based on the checkbox criteria
            this.remove( model.get() );

            /**
             * @param rand Vector from Sorting Component
             *
             */

            Vector< String > rand = new Vector<>();
            SortingManager sortingManager = new SortingManager();

            if ( box1.isSelected() )
            {
                // call method for first sort
                sortingManager.setStringMethod( "SortByNumberOfContributorFollowers" );
            }

            if ( box2.isSelected() )
            {
                // call method for second sort
                sortingManager.setStringMethod( "SortByNumberOfForks" );
            }

            if ( box3.isSelected() )
            {
                // call method for third sort
                sortingManager.setStringMethod( "SortByNumberOfReleases" );
            }
            List< String > paths = Trim.getPaths( sortingManager.getSortingMethod().sort( Trim.getRemaining() ) );
            /**
             * reverse
             * ***/
            for ( String path : paths )
            {
                System.out.println( "Found: " + path.substring( 4, path.length() ) );
                rand.add( path.substring( 4, path.length() ) );
            }
            model.set( new FileTree( sit, rand ) );

            this.add( model.get(), BorderLayout.CENTER );
            this.revalidate();
        } );

        box1.addActionListener( e ->
        {
            if ( box1.isSelected() )
            {
                box2.setSelected( false );
                box3.setSelected( false );
            }
        } );
        box2.addActionListener( e ->
        {
            if ( box2.isSelected() )
            {
                box1.setSelected( false );
                box3.setSelected( false );
            }
        } );
        box3.addActionListener( e ->
        {
            if ( box3.isSelected() )
            {
                box1.setSelected( false );
                box2.setSelected( false );
            }
        } );


        box1.setBackground( Color.LIGHT_GRAY );
        box2.setBackground( Color.LIGHT_GRAY );
        box3.setBackground( Color.LIGHT_GRAY );
//        box4.setBackground(Color.LIGHT_GRAY);
        JPanel panelRight = new JPanel();

        panelRight.setLayout( new GridLayout( 3, 1 ) );
        panelRight.add( box1 );
        panelRight.add( box2 );
        panelRight.add( box3 );
//        panelRight.add(box4);
        this.add( panelRight, BorderLayout.WEST );

    }

}
