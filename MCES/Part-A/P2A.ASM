	AREA RESET, CODE
	ENTRY
	LDR R0, =DATA1
	LDR R3,=0X40000000
	MOV R4, #05
	LDR R1, [R0], #04
	SUB R4, R4, #01
BACK
	LDR R2, [R0]
	CMP R1, R2
	BLS LESS
	MOV R1, R2
LESS
	ADD R0, R0, #04
	SUB R4, R4, #01
	CMP R4, #00
	BNE BACK
	STR R1, [R3]
STOP B STOP
	
DATA1 DCD &64, &05, &96, &10, &65
	END