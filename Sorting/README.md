----------------------------------------------------------------------------

> Description: Sorting Algorithm 

> How to Run:

> How to 'Work' with Program: 

> Protocol Description: 

----------------------------------------------------------------------------
> Task 1: 

(1)
	Main structure:
	Advantages:
		- The system makes use of the code provided by making Node object abstract, the Sorter and Branch classes extend Node to implement behavior.
		

	Disadvantages:
		- Static system. No dynamic interaction with the system, the end-user cannot make requests. Runs the tests in main and then breaks or hangs.
		- Cannot handle new incoming requests for more branches and sorters to the system. Allowing dynamic requests requres more tests for the system as well scaling up the system. 
		- The tests provided, while testing different options for numbers of branches and sorters, do not test different methods of the programs or different scales of data. I.E. the system should have multiple tests to see how the varied branch numbers handle different volumes of incoming data. 

(2)
	Experiment: Create two arrays of size 100 and 1,000. Populate with random numbers between 0-100 and 0-1,000. Each of current 3 variations of Branch and Sorter combinations will then run the 3 different types of arrays and print results

| Branch/Sorter Combination     | Array Size | Time (ms) |
| ----------------------------- | ---------- | --------- |
| 0 Branch(es) / 1 Sorter(s)    | 20         | 55        |
| 0 Branch(es) / 1 Sorter(s)    | 100        | 110       |
| 0 Branch(es) / 1 Sorter(s)    | 1000       | 1038      |
| 1 Branch(es) / 2 Sorter(s)    | 20         | 56        |
| 1 Branch(es) / 2 Sorter(s)    | 100        | 331       |
| 1 Branch(es) / 2 Sorter(s)    | 1000       | 1774      |
| 3 Branch(es) / 4 Sorter(s)    | 20         | 125       |
| 3 Branch(es) / 4 Sorter(s)    | 100        | 597       |
| 3 Branch(es) / 4 Sorter(s)    | 1000       | 5554      |

	Analysis: Distribution does not help runtime speed. With more data, more branches and sorters produces five times as long of a run time compared to 1 sorter. I think that this occurs because of the latency of the requests, and the overhead of merging data back together.  

(3)
	Make more branches to append to existing project. One branch will connect to a pre-existing branch and a new sorter creating a dynamic 2 branches and 3 sorters. The other will be connected to this previously created branch as well as branch that has only 2 starters. The result will be 3 branches and 5 sorters.

| Branch/Sorter Combination     | Array Size | Time (ms) |
| ----------------------------- | ---------- | --------- |
| 2 Branch(es) / 3 Sorter(s)    | 20         | 57        |
| 2 Branch(es) / 3 Sorter(s)    | 100        | 427       |
| 2 Branch(es) / 3 Sorter(s)    | 1000       | 3498      |
| 3 Branch(es) / 5 Sorter(s)    | 20         | 135       |
| 3 Branch(es) / 5 Sorter(s)    | 100        | 794       |
| 3 Branch(es) / 5 Sorter(s)    | 1000       | 7602      |

	Result/Explanation: As 0 branches / 1 starter & 1 branch / 2 starters are the smallest means to run this system, the created branches fell inside of the existing range and one extending it. As expected, the two new tests fall within the expected range and scale that the previous tests had followed. The increasing size had minimal effect on the smaller array size, but grew exponentially in time for the greater branch/sorter combinations. In theory, spreading the work across multiple servers should be increasing the efficiency as the array size increases, however this is all being done by one computer across multiple servers. Theory: the times will be reduced when work begins to be spread across multiple systems on multiple servers. 

(4)
	WireShark displays many frames regarding the communication between the connections. Once running, Wireshark showed a lot of communication when run from gradle.  To reduce the number of traffic, filters can be used as well as optimization of the communication in the program. 

> Task 2: 

