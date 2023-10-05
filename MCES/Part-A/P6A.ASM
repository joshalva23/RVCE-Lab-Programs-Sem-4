	; Compute factorial of  a 32 bit number
	AREA RESET, CODE
	ENTRY
	LDR R0, =INPUT
	BL FACT
	LDR R1, =0x40000000
	STR R3, [R1]
STOP B STOP
; begin subroutine
FACT
	LDR R2, [R0]
	CMP R2, #00
	BEQ END1
	MOV R3, R2
LOOP
	SUB R2, #01
	CMP R2, #00
	MULNE R3, R2, R3
	BNE LOOP
	B END2; return
END1
	MOV R3, #01
END2
	BX LR
;end subroutine
INPUT DCD &05
	END