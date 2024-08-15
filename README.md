# EMV TLV Parser
An Android application that interprets and parses TLV (Tag-Length-Value) data, specifically for EMV (Europay, MasterCard, and Visa) protocols.

## Features

- Parses TLV data according to EMV specifications.
- Identifies known tags and extracts the length and value fields.
- Provides error handling for unknown tags or incomplete data.
- Displays parsed TLV information in a user-friendly format.


## Prerequisites

- **Android Studio**


## How to Run the Application

**Clone the Repository**:
   ```bash
   git clone https://github.com/dikascode/CustomParser.git
   cd emv-tlv-parser(location where you cloned custom parser)
   ```


**Open the Project in Android Studio**:
- Launch Android Studio and select Open an existing project.
- Navigate to the cloned repository and click OK.


**Build the Project**:
- Ensure your project syncs with Gradle by clicking Sync Now if prompted.
- Build the project using the Build > Rebuild Project.

**Run the Application**:
- Connect an Android device or use an emulator.
- Click the Run button in Android Studio.

## Key Configuration Details
- Compile SDK Version: 34
- Min SDK Version: 30
- Target SDK Version: 34
- Kotlin JVM Target: 1.8


## Assumptions Made During Development
- The input data provided by the user is in a hexadecimal string format, with spaces allowed between characters.
- Only known EMV tags as per the TagRegistry are recognized. If an unrecognized tag is encountered, an error message is returned.
- The length field is assumed to be a single byte (2 hexadecimal characters).


## Instructions on How to Test the Application
- Run the application on an Android device or emulator.
- Enter valid TLV data in the text input field (e.g., 9F02060000000001009F0306000000000000).
- Click the Parse button to view the parsed output in the display area.


## Edge Case Testing:
- Test with incomplete TLV data (e.g., missing length or value) to ensure error messages are correctly displayed.
- Enter TLV data with unknown tags to confirm that appropriate error handling occurs.
