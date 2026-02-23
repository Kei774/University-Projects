# Traffic Data Analysis and Histogram Visualizer

A Python application that processes real world traffic survey CSV data, performs statistical analysis, and visualizes hourly vehicle distribution using a graphical histogram.

This project strengthened my understanding of data processing, object oriented programming, and basic data visualization.

---

## Overview

The system allows a user to:

* Enter a survey date
* Load the corresponding traffic CSV file
* Calculate multiple traffic statistics
* Generate a histogram comparing two junctions
* Process multiple datasets in one run

It combines data analysis with graphical output.

Built using:

* Python
* File handling
* Object oriented programming
* Zelle graphics library

---

## Features

Data Processing

* Total vehicles recorded
* Total trucks
* Electric vehicles count
* Two wheeled vehicles count
* Buses heading north from Elm Avenue
* Vehicles going straight
* Percentage of trucks
* Average bicycles per hour
* Speeding vehicles
* Junction based vehicle totals
* Scooter percentage per junction
* Peak traffic hour detection
* Rain hour detection

Visualization

* Hourly histogram for 24 hours
* Side by side comparison of two junctions
* Auto scaling based on maximum frequency
* Legend and labeled bars

Multi File Support

* User can analyze multiple dates in a loop
* Results appended to a text file

---

## System Structure

HistogramApp
Handles graphical window creation and histogram rendering.

MultiCSVProcessor
Handles user input, CSV loading, validation, calculations, and looping logic.

This separation improved modularity and maintainability.

---

## What I Learned

Object Oriented Design
I separated visualization and data processing into independent classes.

Input Validation
I implemented robust date validation with range checks and error handling.

File Handling
I read CSV files safely and handled missing file errors gracefully.

Data Aggregation
I used lists, counters, sets, and loops to compute structured statistics.

Time Based Analysis
I parsed timestamps and grouped data by hour to detect peak traffic periods.

Data Visualization
I converted numerical results into scaled graphical histograms.

Error Handling
I used try except blocks to prevent crashes from invalid input or malformed rows.

---

## How to Run

1. Ensure traffic CSV files follow this format:
   traffic_dataDDMMYYYY.csv

2. Run the Python script.

3. Enter the required date when prompted.

4. View analysis in the terminal and histogram window.

5. Results are saved in results.txt.

---

## Why I Built This

This project demonstrates my ability to:

* Process structured datasets
* Perform analytical computations
* Build modular Python systems
* Implement graphical visualization
* Handle multiple datasets interactively

It represents a strong step forward in applying programming to real world data analysis problems.