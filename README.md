# FFX-WeaponArmor-Value-Calc
Calculates the value of armor and weapons in Final Fantasy X based on abilities and slots attached.
Based on the data provided by: https://gamefaqs.gamespot.com/ps2/197344-final-fantasy-x/faqs/16153

Basic functionality complete. The user can specify the total number of ability slots 1 - 4, and 
a list of abilities less than or equal to the total specified within "equipment.txt". The script reads one ability per
line so ensure that the abilities are listed in that fashion. If there is at least one open slot in the setup, the 
program will print a list of abilities and the added value they will bring if applied to the next open slot.
The script doesn't account for extra whitespaces in the text file and is ability names are case sensitive. No real 
error handling if not used 
as intended.

Improvements to be made:
-Make the script more lenient about .txt formatting
-Meaningful error handling
-build an executable
-gui instead of txt file
-~~given at least one used slot and at least one open slot, return a list of abilities that will increase
  the current equipment's value. Provide value difference and recipe.~~
-get data on recipe items
  -in-store item cost
  -get bribe cost where cost = bribe/(num of that item recieved)
  -given a list of possible abilities by the user, number of total slots, and number of slots that should be not empty,
  calculate the combination of abilities that will yield the highest possible value. 
    -Option to adjust for cost of buying/bribing
      -Script should find the lowest possible cost of the recipe automatically, and say if its bribing/buying
    -Option to include non buy methods such as kill/steal
      -this would be a massive undertaking, requiring kill/steal/overkill data, which would be doable. 
        Additionaly, adjusting for possible monster combinations by battle/area would result in the 
        best possible yield scenario for a given item, this is doable but not for me haha, unless
        someone hands me the data I need.

    
  
