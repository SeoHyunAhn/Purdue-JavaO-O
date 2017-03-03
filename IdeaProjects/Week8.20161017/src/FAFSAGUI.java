import jdk.nashorn.internal.codegen.*;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.ActionListener;

import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.S;
import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.W;


/**
 * Created by student on 10/18/16.
 */
public class FAFSAGUI extends JFrame {
    public static void main(String[] args) {
        int ageInt, creditHoursInt, studentIncomeInt, answer;
        double parentIncomeInt;
        String classStanding, age, creditHours, studentIncome, parentIncome;
        boolean isAcceptedStudent =true, isSSregistered = true, hasSSN=true, hasValidResidency=true, isIndependent=true;
        JFrame frame = new JFrame();
        do {
            JOptionPane.showMessageDialog(frame, "Welcome to FAFSA!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
            Object[] options = {"Yes", "No"};
            int isAStudent = JOptionPane.showConfirmDialog(frame, "Have you been accepted into a degree or certificate program?", "Program Acceptance", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (isAStudent == JOptionPane.YES_OPTION) {
                isAcceptedStudent = true;
            }
            int isregistered = JOptionPane.showOptionDialog(frame, "Are you registered for the selective service?", " Selective Service", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (isregistered == JOptionPane.YES_OPTION) {
                isSSregistered = true;
            }
            int SSN = JOptionPane.showOptionDialog(frame, "Do you have a social security number?", "Social Security Number", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (SSN == JOptionPane.YES_OPTION) {
                hasSSN = true;
            }
            int hasResidency = JOptionPane.showOptionDialog(frame, "Do you have valid residency status?", "Residency Status", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (hasResidency == JOptionPane.YES_OPTION) {
                hasValidResidency = true;
            }

            do {
                age = JOptionPane.showInputDialog(frame, "How old are you", "Age", JOptionPane.QUESTION_MESSAGE);
                ageInt = Integer.parseInt(age);
                if (ageInt < 0) {
                    JOptionPane.showMessageDialog(frame, "Age cannot be a negative number.", "Error: Age", JOptionPane.ERROR_MESSAGE);
                }
            } while (ageInt < 0);

            do {
                creditHours = JOptionPane.showInputDialog(frame, "How many credit hours do you plan on taking?", "Credit Hours", JOptionPane.QUESTION_MESSAGE);
                creditHoursInt = Integer.parseInt(creditHours);
                if (creditHoursInt > 25 && creditHoursInt < 0) {
                    JOptionPane.showMessageDialog(frame, "Credit hours must be between 1 and 24, inclusive.", "Error: Credit Hours", JOptionPane.ERROR_MESSAGE);
                }
            } while (creditHoursInt > 25 && creditHoursInt < 0);

            do {
                studentIncome = JOptionPane.showInputDialog(frame, "What is your total yearly income?", "Student Income", JOptionPane.QUESTION_MESSAGE);
                studentIncomeInt = Integer.parseInt(studentIncome);
                if (studentIncomeInt < 0) {
                    JOptionPane.showMessageDialog(frame, "Income cannot be a negative number.", "Error: Student Income", JOptionPane.ERROR_MESSAGE);
                }
            } while (studentIncomeInt < 0);

            do {
                parentIncome = JOptionPane.showInputDialog(frame, "What is your parent's total yearly income?", "Parent Income", JOptionPane.QUESTION_MESSAGE);
                parentIncomeInt = Integer.parseInt(parentIncome);
                if (studentIncomeInt < 0) {
                    JOptionPane.showMessageDialog(frame, "Income cannot be a negative number.", "Error: Parent Income", JOptionPane.ERROR_MESSAGE);
                }
            } while (studentIncomeInt < 0);

            int Independent = JOptionPane.showOptionDialog(frame, "Are you a dependent?", "Dependency", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (Independent == JOptionPane.YES_OPTION) {
                isIndependent = true;
            }


            String[] Selections = {"Freshman", "Sophomore", "Junior", "Senior", "Graduate"};
            classStanding = (String) JOptionPane.showInputDialog(frame, "What is your current class standing?", "Class Standing", JOptionPane.PLAIN_MESSAGE, null, Selections, Selections[1]);
            FAFSA object = new FAFSA(isAcceptedStudent, isSSregistered, hasSSN, hasValidResidency, isIndependent, ageInt, creditHoursInt, studentIncomeInt, parentIncomeInt, classStanding);
            double Loan = object.calcStaffordLoan();
            double Grant = object.calcFederalGrant();
            double Work = object.calcWorkStudy();
            double FAAmount = object.calcFederalAidAmount();
            boolean Eligible = object.isFederalAidEligible();

            if (Eligible == true) {
                JOptionPane.showMessageDialog(frame, "Loans: " + Loan + "\nGrant: " + Grant + "\nWork Study: " + Work+"\n------------------\nTotal: " + FAAmount, "FAFSA Results", JOptionPane.INFORMATION_MESSAGE);
            }

         answer= JOptionPane.showOptionDialog(frame, "Would you like to complete another Application?", "Continue", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[1]);
            if (answer==JOptionPane.NO_OPTION)
                System.exit(0);
        }while (answer==JOptionPane.YES_OPTION);

        }
    }

