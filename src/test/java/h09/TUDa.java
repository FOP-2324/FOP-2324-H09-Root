package h09;

import h09.room.LectureHall;
import h09.room.SeminarRoom;
import h09.stack.StackOfObjects;

public class TUDa {

    public static StackOfObjects<LectureHall> stackOfLectureHalls() {
        StackOfObjects<LectureHall> stack = new StackOfObjects<>();
        stack.push(new LectureHall("B101/52", 84));
        stack.push(new LectureHall("B201/147", 187));
        stack.push(new LectureHall("L101/24K", 100));
        stack.push(new LectureHall("L203/05", 107));
        stack.push(new LectureHall("L203/06", 107));
        stack.push(new LectureHall("L203/6", 272));
        stack.push(new LectureHall("L301/A91", 114));
        stack.push(new LectureHall("L301/A92", 69));
        stack.push(new LectureHall("L301/A93", 255));
        stack.push(new LectureHall("L402/1", 683));
        stack.push(new LectureHall("L402/2", 335));
        stack.push(new LectureHall("L402/201", 198));
        stack.push(new LectureHall("L402/202", 198));
        stack.push(new LectureHall("L506/11", 120));
        stack.push(new LectureHall("S101/A01", 298));
        stack.push(new LectureHall("S101/A02", 116));
        stack.push(new LectureHall("S101/A03", 216));
        stack.push(new LectureHall("S101/A04", 216));
        stack.push(new LectureHall("S101/A1", 732));
        stack.push(new LectureHall("S101/A2", 68));
        stack.push(new LectureHall("S101/A3", 68));
        stack.push(new LectureHall("S101/A4", 164));
        stack.push(new LectureHall("S101/A5", 164));
        stack.push(new LectureHall("S103/123", 148));
        stack.push(new LectureHall("S103/221", 210));
        stack.push(new LectureHall("S103/223", 148));
        stack.push(new LectureHall("S103/226", 210));
        stack.push(new LectureHall("S103/23", 148));
        stack.push(new LectureHall("S105/122", 372));
        stack.push(new LectureHall("S115/021", 15));
        stack.push(new LectureHall("S115/133", 72));
        stack.push(new LectureHall("S202/C110", 80));
        stack.push(new LectureHall("S202/C120", 80));
        stack.push(new LectureHall("S202/C205", 257));
        stack.push(new LectureHall("S204/213", 87));
        stack.push(new LectureHall("S206/030", 484));
        stack.push(new LectureHall("S207/109", 110));
        stack.push(new LectureHall("S207/167", 88));
        stack.push(new LectureHall("S208/171", 198));
        stack.push(new LectureHall("S214/24", 119));
        stack.push(new LectureHall("S217/103", 108));
        stack.push(new LectureHall("S306/051", 286));
        stack.push(new LectureHall("S306/052", 103));
        stack.push(new LectureHall("S306/053", 81));
        stack.push(new LectureHall("S311/0012", 239));
        stack.push(new LectureHall("S311/006", 152));
        stack.push(new LectureHall("S311/08", 455));
        stack.push(new LectureHall("S313/30", 150));
        stack.push(new LectureHall("S402/101", 157));
        return stack;
    }

    public static StackOfObjects<SeminarRoom> stackOfSeminarRooms() {
        StackOfObjects<SeminarRoom> stack = new StackOfObjects<>();
        stack.push(new SeminarRoom("B102/1", 24));
        stack.push(new SeminarRoom("B108/1", 20));
        stack.push(new SeminarRoom("B201/20", 36));
        stack.push(new SeminarRoom("B201/35", 20));
        stack.push(new SeminarRoom("B202/12", 40));
        stack.push(new SeminarRoom("B202/30", 60));
        stack.push(new SeminarRoom("B203/109", 48));
        stack.push(new SeminarRoom("B203/242", 24));
        stack.push(new SeminarRoom("B261/102", 50));
        stack.push(new SeminarRoom("L101/141", 20));
        stack.push(new SeminarRoom("L101/161", 20));
        stack.push(new SeminarRoom("L101/328K", 45));
        stack.push(new SeminarRoom("L101/72", 16));
        stack.push(new SeminarRoom("L101/73", 28));
        stack.push(new SeminarRoom("L201/127", 12));
        stack.push(new SeminarRoom("L201/128", 24));
        stack.push(new SeminarRoom("L201/228", 24));
        stack.push(new SeminarRoom("L201/77", 48));
        stack.push(new SeminarRoom("L202/266", 24));
        stack.push(new SeminarRoom("L202/39", 20));
        stack.push(new SeminarRoom("L202/762", 40));
        stack.push(new SeminarRoom("L202/A5", 32));
        stack.push(new SeminarRoom("L202/A6", 29));
        stack.push(new SeminarRoom("L204/F2", 50));
        stack.push(new SeminarRoom("L204/F5", 15));
        stack.push(new SeminarRoom("L205/130", 15));
        stack.push(new SeminarRoom("L301/110", 30));
        stack.push(new SeminarRoom("L301/111", 30));
        stack.push(new SeminarRoom("L301/150", 20));
        stack.push(new SeminarRoom("L301/174", 16));
        stack.push(new SeminarRoom("L301/210", 40));
        stack.push(new SeminarRoom("L301/253", 30));
        stack.push(new SeminarRoom("L301/311", 20));
        stack.push(new SeminarRoom("L301/353", 10));
        stack.push(new SeminarRoom("L301/36", 10));
        stack.push(new SeminarRoom("L301/370", 30));
        stack.push(new SeminarRoom("L301/43", 25));
        stack.push(new SeminarRoom("L301/436", 40));
        stack.push(new SeminarRoom("L301/455", 20));
        stack.push(new SeminarRoom("L301/471", 20));
        stack.push(new SeminarRoom("L301/476", 20));
        stack.push(new SeminarRoom("L301/477", 20));
        stack.push(new SeminarRoom("L301/478", 20));
        stack.push(new SeminarRoom("L301/479", 20));
        stack.push(new SeminarRoom("L301/510", 25));
        stack.push(new SeminarRoom("L301/511", 20));
        stack.push(new SeminarRoom("L301/530", 35));
        stack.push(new SeminarRoom("L301/550", 35));
        stack.push(new SeminarRoom("L301/570", 40));
        stack.push(new SeminarRoom("L301/7", 25));
        stack.push(new SeminarRoom("L301/79", 30));
        stack.push(new SeminarRoom("L301/A98", 36));
        stack.push(new SeminarRoom("L402/203", 24));
        stack.push(new SeminarRoom("L402/204", 20));
        stack.push(new SeminarRoom("L402/226", 18));
        stack.push(new SeminarRoom("L402/227", 14));
        stack.push(new SeminarRoom("L402/228", 24));
        stack.push(new SeminarRoom("L402/229", 16));
        stack.push(new SeminarRoom("L402/230", 16));
        stack.push(new SeminarRoom("L402/231", 16));
        stack.push(new SeminarRoom("L402/3", 22));
        stack.push(new SeminarRoom("L402/301", 40));
        stack.push(new SeminarRoom("L402/304", 20));
        stack.push(new SeminarRoom("L402/332", 10));
        stack.push(new SeminarRoom("L402/333", 14));
        stack.push(new SeminarRoom("L402/334", 16));
        stack.push(new SeminarRoom("L402/336", 12));
        stack.push(new SeminarRoom("L402/337", 10));
        stack.push(new SeminarRoom("L402/338", 25));
        stack.push(new SeminarRoom("L402/340", 40));
        stack.push(new SeminarRoom("L402/4", 24));
        stack.push(new SeminarRoom("L402/5", 24));
        stack.push(new SeminarRoom("L402/6", 16));
        stack.push(new SeminarRoom("L501/32", 20));
        stack.push(new SeminarRoom("L501/33", 20));
        stack.push(new SeminarRoom("L501/427", 35));
        stack.push(new SeminarRoom("L501/43", 31));
        stack.push(new SeminarRoom("L501/45a", 28));
        stack.push(new SeminarRoom("L501/45b", 35));
        stack.push(new SeminarRoom("L506/26", 20));
        stack.push(new SeminarRoom("L506/32", 14));
        stack.push(new SeminarRoom("L601/417", 20));
        stack.push(new SeminarRoom("L603/CiP", 13));
        stack.push(new SeminarRoom("S102/144", 20));
        stack.push(new SeminarRoom("S102/244", 16));
        stack.push(new SeminarRoom("S102/330", 18));
        stack.push(new SeminarRoom("S102/331", 18));
        stack.push(new SeminarRoom("S102/34", 22));
        stack.push(new SeminarRoom("S102/344", 18));
        stack.push(new SeminarRoom("S102/36", 40));
        stack.push(new SeminarRoom("S103/015", 24));
        stack.push(new SeminarRoom("S103/017", 40));
        stack.push(new SeminarRoom("S103/10", 14));
        stack.push(new SeminarRoom("S103/100", 44));
        stack.push(new SeminarRoom("S103/102", 19));
        stack.push(new SeminarRoom("S103/104", 40));
        stack.push(new SeminarRoom("S103/107", 40));
        stack.push(new SeminarRoom("S103/109", 37));
        stack.push(new SeminarRoom("S103/11", 21));
        stack.push(new SeminarRoom("S103/110", 26));
        stack.push(new SeminarRoom("S103/112", 32));
        stack.push(new SeminarRoom("S103/113", 56));
        stack.push(new SeminarRoom("S103/116", 32));
        stack.push(new SeminarRoom("S103/12", 32));
        stack.push(new SeminarRoom("S103/121", 60));
        stack.push(new SeminarRoom("S103/125", 40));
        stack.push(new SeminarRoom("S103/126", 25));
        stack.push(new SeminarRoom("S103/161", 30));
        stack.push(new SeminarRoom("S103/164", 20));
        stack.push(new SeminarRoom("S103/175", 64));
        stack.push(new SeminarRoom("S103/20", 25));
        stack.push(new SeminarRoom("S103/204", 48));
        stack.push(new SeminarRoom("S103/209", 37));
        stack.push(new SeminarRoom("S103/21", 22));
        stack.push(new SeminarRoom("S103/25", 30));
        stack.push(new SeminarRoom("S103/312", 24));
        stack.push(new SeminarRoom("S103/9", 37));
        stack.push(new SeminarRoom("S105/22", 47));
        stack.push(new SeminarRoom("S105/23", 48));
        stack.push(new SeminarRoom("S105/24", 48));
        stack.push(new SeminarRoom("S113/118", 10));
        stack.push(new SeminarRoom("S113/169", 30));
        stack.push(new SeminarRoom("S113/266", 44));
        stack.push(new SeminarRoom("S115/020", 37));
        stack.push(new SeminarRoom("S115/127", 30));
        stack.push(new SeminarRoom("S115/128", 12));
        stack.push(new SeminarRoom("S115/138", 35));
        stack.push(new SeminarRoom("S115/238", 35));
        stack.push(new SeminarRoom("S118/2", 30));
        stack.push(new SeminarRoom("S206/36", 25));
        stack.push(new SeminarRoom("S207/53", 13));
        stack.push(new SeminarRoom("S209/04", 8));
        stack.push(new SeminarRoom("S214/208", 40));
        stack.push(new SeminarRoom("S215/134", 35));
        stack.push(new SeminarRoom("S215/204K", 18));
        stack.push(new SeminarRoom("S215/234", 26));
        stack.push(new SeminarRoom("S215/404K", 24));
        stack.push(new SeminarRoom("S215/409K", 18));
        stack.push(new SeminarRoom("S215/51", 42));
        stack.push(new SeminarRoom("S217/27", 10));
        stack.push(new SeminarRoom("S220/10", 18));
        stack.push(new SeminarRoom("S220/9", 18));
        stack.push(new SeminarRoom("S306/146", 56));
        stack.push(new SeminarRoom("S306/238", 24));
        stack.push(new SeminarRoom("S306/321", 14));
        stack.push(new SeminarRoom("S313/10", 48));
        stack.push(new SeminarRoom("S313/110", 25));
        stack.push(new SeminarRoom("S313/112", 25));
        stack.push(new SeminarRoom("S313/117", 12));
        stack.push(new SeminarRoom("S313/215", 25));
        stack.push(new SeminarRoom("S320/111", 50));
        stack.push(new SeminarRoom("S320/18", 48));
        stack.push(new SeminarRoom("S320/4", 24));
        stack.push(new SeminarRoom("S320/5", 32));
        stack.push(new SeminarRoom("S407/103", 18));
        stack.push(new SeminarRoom("S407/107", 18));
        stack.push(new SeminarRoom("S407/108", 18));
        stack.push(new SeminarRoom("S407/303", 16));
        stack.push(new SeminarRoom("S407/304", 20));
        stack.push(new SeminarRoom("S407/306", 18));
        stack.push(new SeminarRoom("S407/4", 25));
        stack.push(new SeminarRoom("S407/8", 100));
        stack.push(new SeminarRoom("S410/1", 60));
        stack.push(new SeminarRoom("S422/2", 20));
        stack.push(new SeminarRoom("S422/3", 25));
        stack.push(new SeminarRoom("S422/4", 15));
        stack.push(new SeminarRoom("S422/5", 33));
        stack.push(new SeminarRoom("S422/6", 26));
        stack.push(new SeminarRoom("S422/9", 30));
        stack.push(new SeminarRoom("W101/4", 28));
        return stack;
    }
}
