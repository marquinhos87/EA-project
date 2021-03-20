j_all: j25 j100 j250

jlogin25:
	rm -r -f login/login-25-clients-5-mins/
	jmeter -n -t login/login-25-clients-5-mins.jmx -l login/login-25-clients-5-mins/results.txt -e -o login/login-25-clients-5-mins/

jlogin100:
	rm -r -f login/login-100-clients-5-mins/
	jmeter -n -t login/login-100-clients-5-mins.jmx -l login/login-100-clients-5-mins/results.txt -e -o login/login-100-clients-5-mins/

jlogin250:
	rm -r -f login/login-250-clients-5-mins/
	jmeter -n -t login/login-250-clients-5-mins.jmx -l login/login-250-clients-5-mins/results.txt -e -o login/login-250-clients-5-mins/