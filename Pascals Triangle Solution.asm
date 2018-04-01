TITLE Pascal's Triangle

; Program Obtains an integer from the user, and
; generates Pascal's Triangle from 1 to the integer
; entered by the user
; Author: Adam Androulidakis

INCLUDE Irvine32.inc

Binomial proto

.data
endl EQU <0dh, 0ah>
prompt BYTE "Input a positive integer: ", 0
invalidMsg BYTE "Invalid Entry. Height must be 1 to 10...", 0
space BYTE "  ", 0
inputValue  DWORD ?
currentRow DWORD ?
spaceCount DWORD ?
MAXVALUE EQU 10

.code
main PROC
call Clrscr				; Clear the screen

Start :
mov  edx, offset prompt		; Get input from user
call WriteString
call ReadDec				; Value entered into eax

; Initialize variables for current iteration of user input
mov inputValue, eax
mov currentRow, 0
mov spaceCount, eax

; Check values entered
; End program if 0 is entered
cmp eax, 0; If input = 0
jz  ProgramEnd; Quit program
cmp eax, MAXVALUE; If input > MAXVALUE
jg InvalidEntry


Row:
inc currentRow; next row, or set to 1 on first iteration of Row loop
mov eax, inputValue
mov spaceCount, eax
call crlf

; Add series of spaces to center the row of the triangle
AlignRow:
	mov edx, OFFSET space
	call WriteString
	dec spaceCount
	mov ebx, spaceCount
cmp ebx, currentRow
jg AlignROw

mov ecx, currentRow
add ecx, 1				; prime LoopK

; Calculate coefficients of(a + b)n
LoopK:
dec ecx
push ecx
push currentRow

call Binomial
call WriteDec

; Write spaces separating different integers
mov edx, offset space
call WriteString

cmp ecx, 0				; If not yet zero
jg  LoopK					; calculate and print next coefficient

call crlf					; Additional line for next row of triangle
			
mov ebx, currentRow
cmp ebx, inputValue			;  if inputValue < currentRow
jl Row					;  Jump to next row

jmp Start					; Get next iteration of input from user

;Invalid input size so display error message and start again
InvalidEntry:
mov edx, OFFSET invalidMsg
call WriteString
call crlf			
jmp Start

ProgramEnd :
exit

main endp
END main