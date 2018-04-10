import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenu;

public class ClassOptimizerGUI extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private JPanel				contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					ClassOptimizerGUI frame = new ClassOptimizerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public ClassOptimizerGUI() {
		ClassOptimizer classOptimizer = new ClassOptimizer();
		
		setTitle("Schedule Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ButtonGroup btnGrpIntensity = new ButtonGroup();
		
		ButtonGroup btnGrpStartTime = new ButtonGroup();
		
		ButtonGroup btnGrpEndTime = new ButtonGroup();
		
		JPanel panelBodyContent = new JPanel();
		panelBodyContent.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panelBodyContent, BorderLayout.CENTER);
		panelBodyContent.setLayout(new BorderLayout(0, 0));
		
		JPanel panelLoadFile = new JPanel();
		panelBodyContent.add(panelLoadFile, BorderLayout.NORTH);
		panelLoadFile.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelLoadFile.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelLoadFile = new JLabel("<no file>");
		labelLoadFile.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnLoadCoursePossibilities = new JButton("Load Courses");
		btnLoadCoursePossibilities.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				chooser.setFileFilter(filter);
				
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					labelLoadFile.setName(chooser.getSelectedFile().getAbsolutePath());
					labelLoadFile.setText(chooser.getSelectedFile().getName());
				}
			}
		});
		
		panelLoadFile.add(btnLoadCoursePossibilities);
		panelLoadFile.add(labelLoadFile);
		
		JPanel panelScheduleRestrictionsPreferences = new JPanel();
		panelBodyContent.add(panelScheduleRestrictionsPreferences, BorderLayout.CENTER);
		panelScheduleRestrictionsPreferences.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelScheduleRestrictionsPreferences.setLayout(new BorderLayout(0, 0));
		
		JPanel panelScheduleRestrictions = new JPanel();
		panelScheduleRestrictionsPreferences.add(panelScheduleRestrictions, BorderLayout.CENTER);
		panelScheduleRestrictions.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelScheduleRestrictions.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelAddedRestrictions = new JPanel();
		panelAddedRestrictions.setBorder(new TitledBorder(null, "Added Restrictions", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelScheduleRestrictions.add(panelAddedRestrictions);
		panelAddedRestrictions.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtpnRestrictions = new JTextPane();
		txtpnRestrictions.setText("Days,Start Time,End Time");
		//panelAddedRestrictions.add(txtpnRestrictions, BorderLayout.CENTER);

		JScrollPane scrollPaneRestrictions = new JScrollPane(txtpnRestrictions);
		panelAddedRestrictions.add(scrollPaneRestrictions, BorderLayout.CENTER);
		
		JPanel panelAddRestriction = new JPanel();
		panelScheduleRestrictions.add(panelAddRestriction);
		panelAddRestriction.setBorder(new TitledBorder(null, "Add Restriction", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelAddRestriction.setLayout(new BorderLayout(0, 0));
		
		JPanel panelDateTime = new JPanel();
		panelDateTime.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelAddRestriction.add(panelDateTime, BorderLayout.CENTER);
		panelDateTime.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelDay = new JPanel();
		panelDay.setBorder(new TitledBorder(null, "Days", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDateTime.add(panelDay);
		panelDay.setLayout(new GridLayout(0, 5, 0, 0));
		
		JCheckBox chckbxMonday = new JCheckBox("M");
		chckbxMonday.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxMonday.setVerticalTextPosition(SwingConstants.TOP);
		chckbxMonday.setHorizontalTextPosition(SwingConstants.CENTER);
		panelDay.add(chckbxMonday);
		
		JCheckBox chckbxTuesday = new JCheckBox("T");
		chckbxTuesday.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxTuesday.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxTuesday.setVerticalTextPosition(SwingConstants.TOP);
		panelDay.add(chckbxTuesday);
		
		JCheckBox chckbxWednesday = new JCheckBox("W");
		chckbxWednesday.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxWednesday.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxWednesday.setVerticalTextPosition(SwingConstants.TOP);
		panelDay.add(chckbxWednesday);
		
		JCheckBox chckbxThursday = new JCheckBox("Th");
		chckbxThursday.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxThursday.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxThursday.setVerticalTextPosition(SwingConstants.TOP);
		panelDay.add(chckbxThursday);
		
		JCheckBox chckbxFriday = new JCheckBox("F");
		chckbxFriday.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxFriday.setHorizontalTextPosition(SwingConstants.CENTER);
		chckbxFriday.setVerticalTextPosition(SwingConstants.TOP);
		panelDay.add(chckbxFriday);
		
		JSpinner spinnerStartTime  = new JSpinner(new SpinnerDateModel());
		panelDateTime.add(spinnerStartTime);
		DateEditor de_spinnerStartTime = new JSpinner.DateEditor(spinnerStartTime, "hh:mm a");
		de_spinnerStartTime.setBorder(new TitledBorder(null, "Start Time", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		spinnerStartTime.setEditor(de_spinnerStartTime);
		
		JSpinner spinnerEndTime = new JSpinner(new SpinnerDateModel());
		panelDateTime.add(spinnerEndTime);
		DateEditor de_spinnerEndTime = new JSpinner.DateEditor(spinnerEndTime, "hh:mm a");
		de_spinnerEndTime.setBorder(new TitledBorder(null, "End Time", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		spinnerEndTime.setEditor(de_spinnerEndTime);
		
		JButton buttonAddRestriction = new JButton("<--");
		buttonAddRestriction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String daysStr = "";
					
					for (Component c: panelDay.getComponents())	{
						if (((AbstractButton) c).isSelected())	{
							daysStr += ((AbstractButton) c).getText();
						}
					}
					
					if (daysStr.isEmpty())
						throw new NullPointerException();
					
					SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
					String startTime = sdf.format(spinnerStartTime.getValue());
					String endTime = sdf.format(spinnerEndTime.getValue());
					
					txtpnRestrictions.setText(txtpnRestrictions.getText() + '\n' + daysStr + ',' + startTime + ',' + endTime);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error processing the times");
				} catch (NullPointerException e2)	{
					JOptionPane.showMessageDialog(null, "Error: No Weekday Selected");
				}
				
			}
		});
		panelAddRestriction.add(buttonAddRestriction, BorderLayout.SOUTH);
		
		JPanel panelSchedulePreferences = new JPanel();
		panelScheduleRestrictionsPreferences.add(panelSchedulePreferences, BorderLayout.SOUTH);
		panelSchedulePreferences.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelSchedulePreferences.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panelScheduleIntensity = new JPanel();
		panelScheduleIntensity.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Intensity", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSchedulePreferences.add(panelScheduleIntensity);
		panelScheduleIntensity.setLayout(new GridLayout(2, 0, 0, 0));
		
		JRadioButton rdbtnTighter = new JRadioButton("Tighter");
		rdbtnTighter.setSelected(true);
		rdbtnTighter.setHorizontalAlignment(SwingConstants.CENTER);
		panelScheduleIntensity.add(rdbtnTighter);
		
		JRadioButton rdbtnLooser = new JRadioButton("Looser");
		rdbtnLooser.setHorizontalAlignment(SwingConstants.CENTER);
		panelScheduleIntensity.add(rdbtnLooser);
		btnGrpIntensity.add(rdbtnTighter);
		btnGrpIntensity.add(rdbtnLooser);
		
		JPanel panelStartTime = new JPanel();
		panelStartTime.setBorder(new TitledBorder(null, "Start Time", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelSchedulePreferences.add(panelStartTime);
		panelStartTime.setLayout(new GridLayout(2, 1, 0, 0));
		
		JRadioButton rdbtnEarlyStart = new JRadioButton("Early");
		rdbtnEarlyStart.setSelected(true);
		rdbtnEarlyStart.setHorizontalAlignment(SwingConstants.CENTER);
		panelStartTime.add(rdbtnEarlyStart);
		
		JRadioButton rdbtnLateStart = new JRadioButton("Late ");
		rdbtnLateStart.setHorizontalAlignment(SwingConstants.CENTER);
		panelStartTime.add(rdbtnLateStart);
		btnGrpStartTime.add(rdbtnEarlyStart);
		btnGrpStartTime.add(rdbtnLateStart);
		
		JPanel panelEndTime = new JPanel();
		panelEndTime.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "End Time", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSchedulePreferences.add(panelEndTime);
		panelEndTime.setLayout(new GridLayout(2, 1, 0, 0));
		
		JRadioButton rdbtnEarlyEnd = new JRadioButton("Early");
		rdbtnEarlyEnd.setSelected(true);
		rdbtnEarlyEnd.setHorizontalAlignment(SwingConstants.CENTER);
		panelEndTime.add(rdbtnEarlyEnd);
		
		JRadioButton rdbtnLateEnd = new JRadioButton("Late ");
		rdbtnLateEnd.setHorizontalAlignment(SwingConstants.CENTER);
		panelEndTime.add(rdbtnLateEnd);
		btnGrpEndTime.add(rdbtnEarlyEnd);
		btnGrpEndTime.add(rdbtnLateEnd);
		
		JPanel panelSaveFile = new JPanel();
		panelBodyContent.add(panelSaveFile, BorderLayout.SOUTH);
		panelSaveFile.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelSaveFile.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelSaveFolder = new JPanel();
		panelSaveFolder.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelSaveFile.add(panelSaveFolder);
		panelSaveFolder.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel labelSaveFolder = new JLabel("<no file>");
		labelSaveFolder.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnChooseFolder = new JButton("Choose Folder");
		btnChooseFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    	labelSaveFolder.setName(chooser.getSelectedFile().getAbsolutePath());
			    	labelSaveFolder.setText(chooser.getSelectedFile().getName() + '/');
			    }
			}
		});
		panelSaveFolder.add(btnChooseFolder);
		panelSaveFolder.add(labelSaveFolder);
		
		JButton btnGenerate = new JButton("Generate");
		panelSaveFile.add(btnGenerate);
		btnGenerate.addMouseListener(new MouseAdapter() {
					
			@Override
			public void mouseClicked(MouseEvent e) {

				classOptimizer.reInit(labelLoadFile.getName());
				
				String[] lines = txtpnRestrictions.getText().split("\n");
				List <Schedule> schedules = classOptimizer.getSchedules();
				
				for (String line: lines)	{
					try	{
						for (int i = 0; i < schedules.size(); i++)	{
							if (schedules.get(i).conflictsWith(ClassOptimizerIO.weekdaysFromShortNames(line.split(",")[0]), Time.fromString(line.split(",")[1]), Time.fromString(line.split(",")[2])))
								schedules.remove(i--);
						}
					} catch (Exception ex)	{
						
					}
				}
				
				try {
					ClassOptimizerIO.save(labelSaveFolder.getName(), classOptimizer.getSchedules(), (rdbtnTighter.isSelected()) ? new ScheduleIntensityComparator() : Collections.reverseOrder(new ScheduleIntensityComparator()), (rdbtnEarlyStart.isSelected()) ? new ScheduleStartTimeComparator() : Collections.reverseOrder(new ScheduleStartTimeComparator()), (rdbtnEarlyEnd.isSelected()) ? new SchedulesEndTimeComparator() : Collections.reverseOrder(new SchedulesEndTimeComparator()));
					JOptionPane.showMessageDialog(null, "Your course schedules were saved successfully to " + labelSaveFolder.getName() + ".");
				} catch (Exception ioe) {
					JOptionPane.showMessageDialog(null, "An error occurred while saving your course schedule: \n" + ioe, "Input/output error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		this.pack();
	}
	
}
