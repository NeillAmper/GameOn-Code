
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DeleteQuiz extends javax.swing.JFrame {

    private static final String[] FILE_PATH = {"src/QuizData.json", "src/UserData.json"};
    private JSONObject lastDeletedQuiz = null;
    private int lastDeletedRowIndex = -1;

    public DeleteQuiz() {
        initComponents(); // This initializes the form components (auto-generated)
        populateCategorySelection(); // Populates category combo box with available categories
        loadCategoryQuizzes(); // Loads quizzes based on the selected category
        addSearchListener(); // Adds listener to search field for live updates
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BackButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CategorySelection = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        QuizTable = new javax.swing.JTable();
        DeleteButton = new javax.swing.JButton();
        UndoButton = new javax.swing.JButton();
        SearchField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BackButton.setText("<");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Delete Quiz");

        CategorySelection.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Math", "Science", "History", "English" }));
        CategorySelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategorySelectionActionPerformed(evt);
            }
        });

        QuizTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Quiz ID", "Question"
            }
        ));
        jScrollPane1.setViewportView(QuizTable);

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        UndoButton.setText("Undo");
        UndoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(UndoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DeleteButton)
                .addGap(57, 57, 57))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(CategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(BackButton)
                            .addGap(116, 116, 116)
                            .addComponent(jLabel1))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(BackButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CategorySelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeleteButton)
                    .addComponent(UndoButton))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        GameMaster g = new GameMaster("GameMaster");
        g.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_BackButtonActionPerformed

    private void CategorySelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategorySelectionActionPerformed
        loadCategoryQuizzes();
    }//GEN-LAST:event_CategorySelectionActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        int row = QuizTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(DeleteQuiz.this, "Please select a quiz to delete.");
            return;
        }

        String category = (String) QuizTable.getValueAt(row, 0);
        String quizid = (String) QuizTable.getValueAt(row, 1);

        try (FileReader reader = new FileReader(FILE_PATH[0])) {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray quizzes = (JSONArray) root.get("Quizzes");

            for (int i = 0; i < quizzes.size(); i++) {
                JSONObject quiz = (JSONObject) quizzes.get(i);
                if (category.equals(quiz.get("category")) && quizid.equals(quiz.get("quizid"))) {
                    lastDeletedQuiz = quiz;
                    lastDeletedRowIndex = row;
                    quizzes.remove(i);
                    break;
                }
            }

            try (FileWriter writer = new FileWriter(FILE_PATH[0])) {
                writer.write(root.toJSONString());
            }

            ((DefaultTableModel) QuizTable.getModel()).removeRow(row);
            JOptionPane.showMessageDialog(DeleteQuiz.this, "Quiz deleted successfully!");

        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(DeleteQuiz.this, "Error deleting quiz.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void UndoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoButtonActionPerformed
        if (lastDeletedQuiz == null || lastDeletedRowIndex < 0) {
            JOptionPane.showMessageDialog(DeleteQuiz.this, "Nothing to undo.");
            return;
        }

        try (FileReader reader = new FileReader(FILE_PATH[0])) {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray quizzes = (JSONArray) root.get("Quizzes");

            quizzes.add(lastDeletedQuiz);

            try (FileWriter writer = new FileWriter(FILE_PATH[0])) {
                writer.write(root.toJSONString());
            }

            ((DefaultTableModel) QuizTable.getModel()).insertRow(lastDeletedRowIndex, new Object[]{
                lastDeletedQuiz.get("category"),
                lastDeletedQuiz.get("quizid"),
                lastDeletedQuiz.get("question")
            });

            lastDeletedQuiz = null;
            lastDeletedRowIndex = -1;

            JOptionPane.showMessageDialog(DeleteQuiz.this, "Undo successful!");

        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(DeleteQuiz.this, "Failed to undo delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_UndoButtonActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new DeleteQuiz().setVisible(true);
        });
    }

    private void populateCategorySelection() {
        try (FileReader reader = new FileReader(FILE_PATH[0])) {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray quizzes = (JSONArray) root.get("Quizzes");

            Set<String> categories = new HashSet<>();
            for (Object obj : quizzes) {
                JSONObject quiz = (JSONObject) obj;
                categories.add((String) quiz.get("category"));
            }

            CategorySelection.removeAllItems();
            CategorySelection.addItem("All");
            for (String category : categories) {
                CategorySelection.addItem(category);
            }

            if (CategorySelection.getItemCount() > 0) {
                CategorySelection.setSelectedIndex(0);
            }

        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Error loading categories.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadCategoryQuizzes() {
        String selectedCategory = (String) CategorySelection.getSelectedItem();
        if (selectedCategory == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) QuizTable.getModel();
        model.setRowCount(0);

        try (FileReader reader = new FileReader(FILE_PATH[0])) {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray quizzes = (JSONArray) root.get("Quizzes");

            for (Object obj : quizzes) {
                JSONObject quiz = (JSONObject) obj;
                String category = (String) quiz.get("category");

                if (selectedCategory.equals("All") || selectedCategory.equals(category)) {
                    model.addRow(new Object[]{
                        quiz.get("category"),
                        quiz.get("quizid"),
                        quiz.get("question")
                    });
                }
            }

        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Error loading quizzes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addSearchListener() {
        SearchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                handleSearch();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                handleSearch();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                handleSearch();
            }

            private void handleSearch() {
                String keyword = SearchField.getText().trim();
                if (!keyword.isEmpty()) {
                    searchQuizzes(keyword);
                } else {
                    loadCategoryQuizzes();
                }
            }
        });
    }

    private void searchQuizzes(String keyword) {
        DefaultTableModel model = (DefaultTableModel) QuizTable.getModel();
        model.setRowCount(0); // Clear the table before populating

        try (FileReader reader = new FileReader(FILE_PATH[0])) {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray quizzes = (JSONArray) root.get("Quizzes");

            for (Object obj : quizzes) {
                JSONObject quiz = (JSONObject) obj;
                String category = (String) quiz.get("category");
                String question = (String) quiz.get("question");
                String quizid = (String) quiz.get("quizid");

                // CASE-INSENSITIVE SEARCH MATCHING category, quizid, OR question
                if (category.toLowerCase().contains(keyword.toLowerCase())
                        || quizid.toLowerCase().contains(keyword.toLowerCase())
                        || question.toLowerCase().contains(keyword.toLowerCase())) {

                    model.addRow(new Object[]{category, quizid, question});
                }
            }

        } catch (IOException | ParseException e) {
            JOptionPane.showMessageDialog(this, "Search failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JComboBox<String> CategorySelection;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JTable QuizTable;
    private javax.swing.JTextField SearchField;
    private javax.swing.JButton UndoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
