# Sample sim.py

#  Import statements
#from package1 import function_1

import sys
def getStartCounter(noOfBits):
    if noOfBits == 4 :
        return 8
    elif noOfBits == 3:
        return 4
    elif noOfBits == 2:
        return 2
    else:
        return 1
def printSmithOutput(mispredictionCounter,totalPredictions,currentCounter):
    rate = mispredictionCounter / totalPredictions
    rate = rate * 100
    print("OUTPUT:::")
    print("number of predictions: ",totalPredictions)
    print("number of mispredictions: ",mispredictionCounter)
    print("misprediction rate: ",rate)
    print("FINAL COUNTER CONTENT: ",currentCounter)
def simulateSmithCounter(arg,inputData):
    noOfBits= int(arg[2])
    maxCounter = int(2**(noOfBits))
    currentCounter = getStartCounter(noOfBits)
    separationCounter = int(maxCounter/2)
    missPredictedCounter=0
    totalPredictions = len(inputData)
    for input in inputData:
        if "t".__eq__(input) :
            if currentCounter < separationCounter:
                missPredictedCounter=missPredictedCounter+1
            if currentCounter< maxCounter-1:
                currentCounter=currentCounter+1
        else:
            if currentCounter >= separationCounter :
                missPredictedCounter=missPredictedCounter+1
            if currentCounter>0:
                currentCounter=currentCounter-1
    printSmithOutput(missPredictedCounter,totalPredictions,currentCounter)  
# Main
if __name__ == "__main__":

    if len(sys.argv) > 2:
        bimodalPCBits = 0
        gsharePCBits = 0
        globalHistoryBits = 0
        chooserTableBits = 0
        for arg in sys.argv:
            print(arg)
        predictor = sys.argv[1]
        if predictor=="smith":
            f=open(sys.argv[3],"r")
            inputData = []
            for input in f:
                splitdata = input.split()
                if len(splitdata)>=2:
                    inputData.append(splitdata[1].strip())
            f.close()
            simulateSmithCounter(sys.argv,inputData)
                      
        else :
            print("provide valid predictor name")
    else:
        print("invalid input params")

    