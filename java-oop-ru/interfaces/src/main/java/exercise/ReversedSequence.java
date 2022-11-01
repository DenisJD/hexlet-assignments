package exercise;

// BEGIN
class ReversedSequence implements CharSequence {

    private final String str;
    private String revStr;
    private final String[] temp;
    private String[] revTemp;

    ReversedSequence(String pStr) {
        this.str = pStr;
        this.temp = str.split("");
        setRevTemp(this.temp);
        setRevStr();
    }

    private void setRevTemp(String[] arr) {
        String[] reverse = new String[str.length()];
        int num = 0;
        for (int i = temp.length - 1; i >= 0; i--) {
            reverse[num] = arr[i];
            num++;
        }
        this.revTemp = reverse;
    }

    private void setRevStr() {
        StringBuilder res = new StringBuilder();
        for (String ch : revTemp) {
            res.append(ch);
        }
        this.revStr = res.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (String ch : revTemp) {
            res.append(ch);
        }
        return res.toString();
    }

    @Override
    public int length() {
        return revTemp.length;
    }

    @Override
    public char charAt(int i) {
        char[] ch = revStr.toCharArray();
        return ch[i];
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return revStr.substring(i, i1);
    }
}
// END
