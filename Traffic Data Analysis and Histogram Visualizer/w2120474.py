# Task D: Histogram Display
#Author:P.S.R.H Rodrigo
#Date:13/12/2024
#Student ID:20231309
from graphics import *


class HistogramApp:
    def __init__(self, final_date, elm_frequency, hanley_frequency):
    
        """
        Initializes the histogram application with the traffic data and selected date.
        """

        self.elm_frequency = elm_frequency
        self.hanley_frequency = hanley_frequency
        self.final_date = final_date
        #Histogram values
        self.bar_width = 20
        self.gap_width = 10
        self.win_width = 1300
        self.win_height = 600
        self.x_margin = 50
        self.y_margin = 50
        self.max_height = 400
        self.scale_factor = self.max_height / max(max(self.elm_frequency), max(self.hanley_frequency), 1)

    def setup_window(self):
        """
        Sets up the  window and canvas for the histogram.
        """

        #Creating a window

        self.win = GraphWin("Histogram", self.win_width, self.win_height)

        #Setting a background colour

        self.win.setBackground('#f964b0')

    def draw_histogram(self):

        #drawing the X axis 

        x_axis = Line(Point(self.x_margin, self.win_height - self.y_margin), 
                      Point(self.win_width - self.x_margin, self.win_height - self.y_margin))
        x_axis.draw(self.win)

        #calculating possitions and drawing the bars

        for hour in range(24):

            #positions

            elm_left = self.x_margin + hour * (self.bar_width * 2 + self.gap_width)
            hanley_left = elm_left + self.bar_width

            elm_top = self.win_height - self.y_margin - (self.elm_frequency[hour] * self.scale_factor)
            hanley_top = self.win_height - self.y_margin - (self.hanley_frequency[hour] * self.scale_factor)

            #Drawing Elm Avenue bar

            elm_bar = Rectangle(Point(elm_left, self.win_height - self.y_margin), Point(elm_left + self.bar_width, elm_top))
            elm_bar.setFill("#f7f7f7")
            elm_bar.draw(self.win)

            #Drawing hanley highway bar

            hanley_bar = Rectangle(Point(hanley_left, self.win_height - self.y_margin), Point(hanley_left + self.bar_width, hanley_top))
            hanley_bar.setFill("#5b5b5b")
            hanley_bar.draw(self.win)


            #Alocaitng a frequency value above the bar

            elm_bar_txt = Text(Point(elm_left + 10, elm_top - 7), int(self.elm_frequency[hour]))
            elm_bar_txt.setSize(7)
            elm_bar_txt.draw(self.win)
            
            #Alocating a frequency value above the bar

            hanley_bar_txt = Text(Point(hanley_left + 10, hanley_top - 7), int(self.hanley_frequency[hour]))
            hanley_bar_txt.setSize(7)
            hanley_bar_txt.draw(self.win)

            #Adding a hour lables

            hour_label = Text(Point((elm_left + hanley_left + self.bar_width) / 2, self.win_height - self.y_margin + 15), str(hour))
            hour_label.setSize(8)
            hour_label.draw(self.win)

    def add_legend(self):

        """
        Adds a legend to the histogram to indicate which bar corresponds to which junction.
        """
        #adding legends


        #Elm Avenue
        elm_legend_box = Rectangle(Point(80, 50), Point(100, 70))
        elm_legend_box.setFill("#f7f7f7")
        elm_legend_box.draw(self.win)
        elm_legend_text = Text(Point(200, 60), "Elm Avenue/Rabbit Road")
        elm_legend_text.draw(self.win)

        #Hanley Avenue
        hanley_legend_box = Rectangle(Point(80, 80), Point(100, 100))
        hanley_legend_box.setFill("#5b5b5b")
        hanley_legend_box.draw(self.win)
        hanley_legend_text = Text(Point(200, 90), "Hanley Highway/Westway")
        hanley_legend_text.draw(self.win)

    def run(self):

        """
        Runs the  main loop to display the histogram.
        """

        try:
            self.setup_window()
            self.draw_histogram()
            self.add_legend()
            if self.win.isOpen():  # Check if the window is still open
                self.win.getMouse()
        except GraphicsError as e:
            print("")
        finally:
            if not self.win.isClosed():
                self.win.close()



# Task E: Code Loops to Handle Multiple CSV Files
class MultiCSVProcessor:
    def __init__(self):

        """
        Initializes the application for processing multiple CSV files.
        """

        self.current_data = None
        self.validation = "N"

    def date_input(self):

        """
        Prompting and validating a date from user to load a csv file
        """

        # Initialize and validate day
        while True:
            try:
                day = int(input("Please enter the day of the survey in the format dd: "))
                if not (1 <= day <= 31):
                    print("Out of range - values must be in the range 1 and 31.")
                else:
                    break
            except ValueError:
                print("Integer required")
        day_str = f"{day:02}"

        # Initialize and validate month
        while True:
            try:
                month = int(input("Please enter the month of the survey in the format MM: "))
                if not (1 <= month <= 12):
                    print("Out of range - values must be in the range 1 and 12.")
                else:
                    break
            except ValueError:
                print("Integer required")
        month_str = f"{month:02}"

        # Initialize and validate year
        while True:
            try:
                year = int(input("Please enter the year of the survey in the format YYYY: "))
                if not (2000 <= year <= 2024):
                    print("Out of range - value must lie in the range 2000 to 2024.")
                else:
                    break
            except ValueError:
                print("Integer required")
        year_str = str(year)
        final_date = f"{day}{month}{year}"
        self.final_date = final_date
        file_path = f"traffic_data{day_str}{month_str}{year_str}.csv" #creating the file path after merging the day month and year into the DDMMYYYY format
        return file_path

    def load_csv_file(self, file_path):

        """
        Loads a CSV file and processes its data.
        """

        #opening the file using the file path
        while True:
            try:
                data1 = [] #appending the data to this list
                with open(file_path, "r") as fo:
                    for row in fo:
                        data1.append(row.strip().split(','))
                break
            except FileNotFoundError:
                print("File not found. Please re-enter the date.")
                file_path = self.date_input()

        if len(data1) <= 1:
            print("No data in the file.")
            return

        # Start calculations
        num_vehicles = len(data1) - 1  # Calculating the number of vehicles

        num_truck = 0  # Initializing
        for row in data1[1:]:  # Calculating the number of trucks
            if len(row) > 8 and row[8] == "Truck":
                num_truck += 1

        num_electric = 0  # Initializing
        for row in data1[1:]:  # Calculating the number of electric vehicles
            if len(row) > 9 and row[9] == "TRUE":
                num_electric += 1

        num_2wheel = 0  # Initializing
        for row in data1[1:]:  # Calculating the number of two-wheel vehicles
            if len(row) > 8 and row[8] in ["Scooter", "Motorcycle", "Bicycle"]:
                num_2wheel += 1

        buss_Elm_N = 0  # Initializing
        for row in data1[1:]:  # Calculating the number of busses on Elm Avenue/Rabbit Road
            if len(row) > 8 and row[0] == "Elm Avenue/Rabbit Road" and row[4] == "N" and row[8] == "Buss":
                buss_Elm_N += 1

        head_strit = 0  # Initializing
        for row in data1[1:]:  # Calculating vehicles heading straight
            if len(row) > 4 and row[3] == row[4]:
                head_strit += 1

        truck_prec = round((num_truck / num_vehicles) * 100) if num_vehicles > 0 else 0

        count = 0  # Initializing
        for row in data1[1:]:  # Calculating the number of bicycles per hour
            if len(row) > 8 and row[8] == "Bicycle":
                count += 1
        bicycle_perh = round(count / 24)

        speeding_v = 0  # Initializing
        for row in data1[1:]:  # Calculating speeding vehicles
            if len(row) > 7 and int(row[7]) > int(row[6]):
                speeding_v += 1

        num_elm_av_rbt = 0  # Initializing
        for row in data1[1:]:  # Vehicles on Elm Avenue/Rabbit Road
            if len(row) > 0 and row[0] == "Elm Avenue/Rabbit Road":
                num_elm_av_rbt += 1

        num_hly_hg_west = 0  # Initializing
        for row in data1[1:]:  # Vehicles on Hanley Highway/Westway
            if len(row) > 0 and row[0] == "Hanley Highway/Westway":
                num_hly_hg_west += 1

        count = 0  # Initializing
        for row in data1[1:]:  # Scooter percentage on Elm Avenue/Rabbit Road
            if len(row) > 8 and row[8] == "Scooter" and row[0] == "Elm Avenue/Rabbit Road":
                count += 1
        scooter_pct_elm = round((count / num_elm_av_rbt) * 100) if num_elm_av_rbt > 0 else 0

        temp_hour = 0  # Initializing
        time_list = []
        max_vh_ph = 0
        peak_hour = 0

        for row in data1[1:]:  # Finding the highest vehicles in an hour on Hanley Highway/Westway
            if len(row) > 2 and row[0] == "Hanley Highway/Westway":
                time_list.append(row[2].split(':'))

        while temp_hour < 24:
            vehicles_in_hour = 0
            for hour in time_list:
                if int(hour[0]) == temp_hour:
                    vehicles_in_hour += 1

            if max_vh_ph < vehicles_in_hour:  # Update peak hour
                max_vh_ph = vehicles_in_hour
                peak_hour = temp_hour

            temp_hour += 1
        record_tm = f"{peak_hour:02}:00 and {peak_hour + 1:02}:00"  # Peak hour range

        rain_hours = set()  # Initialize rain hours
        for row in data1[1:]:
            if len(row) > 5 and row[5] == "Rain":
                hour = row[2].split(':')[0]
                rain_hours.add(hour)

        tot_rain_h = len(rain_hours)  # Total rain hours






        try:    #error handling for "FileNotFound"
            with open(file_path, "r") as fo:    #opening the file useing the name from file_path
                for row in fo:  #Getting the information row by row
                    data1.append(row.strip().split(','))
        except FileNotFoundError:
            print("File Not Found\n     please re-enter the date.")


        self.elm_data = [0] * 24
        self.hanley_data = [0] * 24

        data2 = data1[1:]  # Assuming the header is removed elsewhere or data1[0] is the header.

        # Calculating the vehicle frequency per hour
        try:    
            for row in data2:
            
                # Extracting the hour from the time
                hour_of_day = int(row[2].split(':')[0])

                # Adding count for Elm Avenue/Rabbit Road
                if row[0] == 'Elm Avenue/Rabbit Road':
                    self.elm_data[hour_of_day] += 1
    
                # Adding count for Hanley Highway/Westway
                elif row[0] == 'Hanley Highway/Westway':
                    self.hanley_data[hour_of_day] += 1

        except (ValueError, IndexError):
            # Skip rows with invalid data
            print("")




        # Display results
        print("*" * 20)
        print(f"Data file selected: {file_path}")
        print("*" * 20)
        print(f"Total vehicles recorded: {num_vehicles}")
        print(f"Total trucks recorded: {num_truck}")
        print(f"Total electric vehicles: {num_electric}")
        print(f"Total two-wheeled vehicles: {num_2wheel}")
        print(f"Busses leaving Elm Avenue/Rabbit Road heading North: {buss_Elm_N}")
        print(f"Vehicles heading straight: {head_strit}")
        print(f"Percentage of trucks: {truck_prec}%")
        print(f"Average bikes per hour: {bicycle_perh}")
        print(f"Vehicles over speed limit: {speeding_v}")
        print(f"Vehicles through Elm Avenue/Rabbit Road: {num_elm_av_rbt}")
        print(f"Vehicles through Hanley Highway/Westway: {num_hly_hg_west}")
        print(f"Percentage of scooters on Elm Avenue/Rabbit Road: {scooter_pct_elm}%")
        print(f"Peak vehicles in an hour: {max_vh_ph} between {record_tm}")
        print(f"Total rain hours: {tot_rain_h}")

        with open("results.txt","a+") as fo:#over-writing / writing a new file
            fo.write(f"{'*'*20}\n")
            fo.write(f"data file selected is {file_path}\n")
            fo.write(f"{'*'*20}\n")
            fo.write(f"The total number of vehicles recorded for this date is {num_vehicles}\n")#writing all the infomation in a text file
            fo.write(f"The total number of trucks recorded for this date is {num_truck}\n")
            fo.write(f"The total number of electric vehicles for this date is {num_electric}\n")
            fo.write(f"The total number of two-wheeled vehicles for this date is {num_2wheel}\n")
            fo.write(f"The total number of Busses leaving Elm Avenue/Rabbit Road heading North is {buss_Elm_N}\n")
            fo.write(f"The total number of Vehicles through both junctions not turning left or right is {head_strit}\n")
            fo.write(f"The percentage of total vehicles recorded that are trucks for this date is {truck_prec}%\n")
            fo.write(f"The average number of Bikes per hour for this date is {bicycle_perh}\n")
            fo.write(f"\n")#new line
            fo.write(f"The total number of Vehicles recorded as over the speed limit for this date is {speeding_v}\n")
            fo.write(f"The total number of vehicles recorded through Elm Avenue/Rabbit Road junction is {num_elm_av_rbt}\n")
            fo.write(f"The total number of vehicles recorded through Hanley Highway/Westway junction is {num_hly_hg_west}\n")
            fo.write(f"{scooter_pct_elm} of vehicles recorded through Elm Avenue/Rabbit Road are scooters. \n")
            fo.write(f"\n")#new line
            fo.write(f"The highest number of vehicles in an hour on Hanley Highway/Westway is {max_vh_ph}\n")
            fo.write(f"The most vehicles through Hanley Highway/Westway were recorded between {record_tm}\n")
            fo.write(f"The number of hours of rain for this date is {tot_rain_h}\n")
            fo.write(f"\n")
            fo.write(f"\n")

    def clear_previous_data(self):

        """
        Clears data from the previous run to process a new dataset.
        """

        self.current_data = None
        self.elm_data= None
        self.hanley_data=None
        self.validation=None

    def handle_user_interaction(self):

        """
        Handles user input for processing multiple files.
        """
        #loop to ask the user to select anither file.
        while True:
            validation = input("Do you want to select another data file for a different date? (Y/N): ").upper()
            if validation in ["Y", "N"]:
                self.validation = validation
                break
            else:
                print("Please enter 'Y' or 'N'.")

    def process_files(self):

        """
        Main loop for handling multiple CSV files until the user decides to quit.
        """

        while True:
            #clearing previous data
            self.clear_previous_data()

            #Promptimg and validating date 
            file_path = self.date_input()

            #Processing csv file
            self.load_csv_file(file_path)

            # Run the histogram app
            app = HistogramApp(self.final_date, self.elm_data, self.hanley_data)
            app.run()

            self.handle_user_interaction()
            if self.validation == "N":
                print("End of code!")
                break



# Instantiate and process files
main_output = MultiCSVProcessor()
main_output.process_files()
