package recursivetree;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**  Container for Graphics examples
  */

public final class RecursiveTree extends JFrame implements ActionListener
{
   public static void main(String args[])
   {
      RecursiveTree mapp = new RecursiveTree();
      mapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   Timer timer; 
   int nBranches = 1;
   int MAXBRANCHES = 10;
   int DELAY = 2000;
   RenderTree rTree;
   
   JButton incButton, decButton;
   JLabel label;
   /** No argument constructor */
   public RecursiveTree()
   {
      super("COMP-10152 : Recursive Graphics");
      Container c = getContentPane();
      JPanel toolbar = new JPanel();
      rTree = new RenderTree();
      label = new JLabel("Layers = 1");  
      incButton = new JButton("Increment Layers");
      incButton.addActionListener(this);
      decButton = new JButton("Decrement Layers");
      decButton.addActionListener(this);

      toolbar.add(label);
      toolbar.add(incButton);
      toolbar.add(decButton);
      
      c.add(toolbar, BorderLayout.NORTH);
      c.add(rTree, BorderLayout.CENTER);

      setSize(1000,1000);
      setLocationRelativeTo(null);  // Center on screen
      setVisible(true);             
      setResizable(false);         
   }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == incButton) nBranches++;
    if (e.getSource() == decButton) nBranches--;
    
    nBranches = Math.max(1,Math.min(nBranches,10));
    rTree.setBranches(nBranches);
    
    label.setText(String.format("Layers = %d", nBranches));
    repaint();
  }

    
}