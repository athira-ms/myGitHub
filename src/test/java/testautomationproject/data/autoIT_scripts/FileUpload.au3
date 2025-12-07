;================================================================================
; FileUpload.au3 (Open this file in SciTE Editor)
;================================================================================

; --- 1. Define Variables ---
; Window Title: Use the exact title you found in Au3Info (e.g., "Open")
$win_title = "Open"

; File Path: **Must be the FULL, ABSOLUTE path to your file**
$file_path = "C:\Users\ems01\OneDrive\Documents\EclipseProgramming\eclipse\TestUpload.txt"

; --- 2. Automation Steps ---

; 1. Wait until the window with the title "Open" is active
WinWaitActive($win_title)

; 2. Insert the file path into the Textbox control
; We are using 'Edit1' (the Control ID) to target the textbox
ControlSetText($win_title, "", "Edit1", $file_path)

; 3. Click the "Open" button
; We are using 'Button1' (the Control ID) to target the Open button
ControlClick($win_title, "", "Button1")