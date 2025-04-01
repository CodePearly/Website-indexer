import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class WebsiteIndexer {
    public static void main(String[] args) {
        // Creating the JFrame
        JFrame frame = new JFrame("Website Indexer");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // URL Input Field
        JTextField urlField = new JTextField("Enter website URL here...");
        frame.add(urlField, BorderLayout.NORTH);
        
        // Result Area
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Index Button
        JButton indexButton = new JButton("Index Website");
        frame.add(indexButton, BorderLayout.SOUTH);
        
        // Button Action Listener
        indexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = urlField.getText();
                resultArea.setText("Indexing website...\n");
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements links = doc.select("a[href]");
                    
                    // Display results in the text area
                    resultArea.append("Title: " + doc.title() + "\n");
                    resultArea.append("Found links:\n");
                    links.forEach(link -> resultArea.append(link.attr("abs:href") + "\n"));
                } catch (IOException ex) {
                    resultArea.setText("Failed to fetch or index the website. Check the URL.");
                }
            }
        });
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
