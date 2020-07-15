j_all: j25 j100 j250

j25:
	rm -r -f login-25-clients-5-mins/
	jmeter -n -t login-25-clients-5-mins.jmx -l login-25-clients-5-mins/results.txt -e -o login-25-clients-5-mins/

j100:
	rm -r -f login-100-clients-5-mins/
	jmeter -n -t login-100-clients-5-mins.jmx -l login-100-clients-5-mins/results.txt -e -o login-100-clients-5-mins/

j250:
	rm -r -f login-250-clients-5-mins/
	jmeter -n -t login-250-clients-5-mins.jmx -l login-250-clients-5-mins/results.txt -e -o login-250-clients-5-mins/