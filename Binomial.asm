TITLE Binomial Coefficient

; This program contains a single function Binomial.
; Author: Adam Androulidakis

INCLUDE Irvine32.inc

.code
;-----------------------------------------------------
Binomial proc
; Get the values to calculate binoamial coefficient
; 
; Receives: n and k from caller
; Returns:  Binomial coefficient in eax
;-----------------------------------------------------

; Save registers
push ecx
push ebx
push edx

mov eax, 0

;get values from stack
mov ecx, [esp + 20]			; Get k
mov edx, [esp + 16]			; Get n

;-Stop conditions--------------------------------------
; if ecx = 0
;	return 0
; if ecx < 0
;	return 0
; if edx > ecx 
;	return 0
; if ecx = 0
;	return 1
; if edx = 0
;	return 1
; if ecx != edx
;	return 1
	cmp ecx, 0
	jl ret0
	cmp edx, 0
	jl ret0
	cmp ecx, edx
	jg ret0
	cmp ecx, 0
	jz ret1
	cmp edx, 0
	jz ret1
	cmp ecx, edx
	jz ret1
;-----------------------------------------------------
     dec edx
     push ecx
     push edx

     call Binomial
     add esp,8
     dec ecx
     mov ebx, eax

     push ecx
     push edx
     call Binomial
     add esp,8
     add eax, ebx

	;restore registers
     pop edx
     pop ebx
     pop ecx
     retn
	
	ret0:
	mov eax, 0
	pop edx
	pop ebx
	pop ecx
	retn

	ret1:
	mov eax, 1
	;restore registers
	pop edx
	pop ebx
	pop ecx
	retn
 

 
Binomial endp
end