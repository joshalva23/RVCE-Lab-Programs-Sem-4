	; SORT GIVEN LIST OF 32BIT NUMBERS USING BUBBLE SORT
	AREA RESET, CODE
	ENTRY
	LDR R0, =ARRAY
	LDMIA R0, {R1-R10}
	MOV SP, #0x40000000
	STMIA SP, {R1-R10}
	MOV R10, #0x40000000
	MOV R1, #10
	SUB R1, #1
LOOP1
	MOV R2, R1
	MOV R4, R10
	
LOOP2
	LDR R0, [R4]
	LDR R5, [R4, #4]
	CMP R0, R5
	BLS SKIP
	MOV R6, R0
	MOV R0, R5
	MOV R5, R6
	STR R0, [R4]
	STR R5, [R4, #4]
SKIP
	ADD R4, #4
	SUBS R2, #1
	BNE LOOP2
	SUBS R1,#1
	BNE LOOP1
STOP B STOP
ARRAY DCD 2, 7,4,5,11,18,3,15,8,0
	END