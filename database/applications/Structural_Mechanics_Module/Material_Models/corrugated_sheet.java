/*
 * corrugated_sheet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:11 by COMSOL 6.3.0.290. */
public class corrugated_sheet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Material_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

    model.param().label("\u51e0\u4f55\u5c5e\u6027");
    model.param().set("theta", "45[deg]");
    model.param().descr("theta", "\u68af\u5f62\u6ce2\u7eb9\u677f\u7684\u6ce2\u7eb9\u8f6e\u5ed3\u89d2\u5ea6");
    model.param().set("R", "0.0254[m]");
    model.param().descr("R", "\u5706\u5f62\u6ce2\u7eb9\u677f\u7684\u6ce2\u7eb9\u534a\u5f84");
    model.param().create("par2");
    model.param("par2").label("\u5171\u7528\u7684\u51e0\u4f55\u548c\u6750\u6599\u5c5e\u6027");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("case", "1", "\u68af\u5f62\u677f\u51e0\u4f55\u7684\u5e03\u5c14\u503c");
    model.param("par2").set("th", "0.00635[m]", "\u6ce2\u7eb9\u677f\u539a\u5ea6");
    model.param("par2").set("c", "0.0508[m]", "\u6ce2\u7eb9\u534a\u6ce2\u957f");
    model.param("par2").set("f", "0.0127[m]", "\u6ce2\u7eb9\u5e45\u5ea6");
    model.param("par2").set("d", "0.1016[m]", "\u6ce2\u7eb9\u677f\u6df1\u5ea6");
    model.param("par2").set("lh", "2*f/sin(theta)+c-2*f/tan(theta)", "\u6ce2\u7eb9\u677f\u534a\u957f");
    model.param("par2").set("I0c", "4*f*cot(theta)*(cos(theta)-1)+2*c", "\u957f\u5ea6\u53c2\u6570");
    model.param("par2").set("I0s", "4*f*sin(theta)", "\u957f\u5ea6\u53c2\u6570");
    model.param("par2").set("I2", "4*f^3/(3*(sin(theta)))+2*f^2*(c-2*f/tan(theta))", "\u60ef\u6027\u77e9");
    model.param("par2").set("Iy", "0.04598132*c^2*th", "\u60ef\u6027\u77e9");
    model.param("par2").set("EE", "21[GPa]", "\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("Nu", "0.3", "\u6cca\u677e\u6bd4");
    model.param("par2").set("L", "1[m]", "\u53c2\u8003\u957f\u5ea6");
    model.param("par2").paramCase().create("case1");
    model.param("par2").paramCase("case1").label("\u68af\u5f62\u6ce2\u7eb9");
    model.param("par2").paramCase().create("case2");
    model.param("par2").paramCase("case2").label("\u5706\u5f62\u6ce2\u7eb9");
    model.param("par2").paramCase("case2").set("case", "2");
    model.param("par2").paramCase("case2").set("c", "2*R");
    model.param("par2").paramCase("case2").set("f", "R");
    model.param("par2").paramCase("case2").set("lh", "pi*R");
    model.param("par2").paramCase("case2").set("I0c", "2*pi*R");
    model.param("par2").paramCase("case2").set("I0s", "I0c");
    model.param("par2").paramCase("case2").set("I2", "pi*R^3");
    model.param("par2").paramCase("case2").set("Iy", "0.19635*c^2*th");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Corrugated_Sheets\\trapezoidal_corrugation.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part2"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Corrugated_Sheets\\round_corrugation.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "case==1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "c", "c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "f", "f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "theta", "theta");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d", "d");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature().move("endif2", 4);
    model.component("comp1").geom("geom1").feature().move("if2", 3);
    model.component("comp1").geom("geom1").feature("if2").set("condition", "case==2");
    model.component("comp1").geom("geom1").run("if2");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "R", "R");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d", "d");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);

    model.param("par2").set("case", "2");

    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d", "0.1016[m]");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d", "d");

    model.param("par2").set("case", "1");

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5bf9 1\uff0c\u6e90");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"pi1_boxsel1", "pi2_boxsel1"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u5bf9 1\uff0c\u76ee\u6807");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"pi1_boxsel2", "pi2_boxsel2"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u5bf9 2\uff0c\u6e90");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel3")
         .set("input", new String[]{"pi1_boxsel3", "pi2_boxsel3"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u5bf9 2\uff0c\u76ee\u6807");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel4")
         .set("input", new String[]{"pi1_boxsel4", "pi2_boxsel4"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("ballsel1").set("posx", "c");
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", "1e-5*c");
    model.component("comp1").geom("geom1").run("ballsel1");
    model.component("comp1").geom("geom1").create("ballsel2", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel2").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("ballsel2").set("posx", "c");
    model.component("comp1").geom("geom1").feature("ballsel2").set("posy", "d");
    model.component("comp1").geom("geom1").feature("ballsel2").set("r", "1e-5*c");
    model.component("comp1").geom("geom1").run("ballsel2");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("unisel5").set("input", new String[]{"ballsel1", "ballsel2"});
    model.component("comp1").geom("geom1").run("unisel5");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("geom1_unisel1");
    model.component("comp1").cpl("intop1").set("method", "summation");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().named("geom1_unisel3");
    model.component("comp1").cpl("intop2").set("method", "summation");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop3").selection().named("geom1_ballsel1");
    model.component("comp1").cpl("intop3").set("method", "summation");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .label("Xia \u7b49\u4eba\u5f97\u51fa\u7684\u89e3\u6790\u521a\u5ea6\u5206\u91cf\u3002");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("DA11_Xia", "2*c/(I0c/intop3(shell.DA11)+I2/intop3(shell.DD11))", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("DA12_Xia", "intop3(shell.DA12/shell.DA11)*DA11_Xia", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("DA22_Xia", "intop3(shell.DA12)*DA12_Xia/intop3(shell.DA11)+lh/c*intop3((shell.DA11*shell.DA22-shell.DA12^2)/shell.DA11)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("DA33_Xia", "c/lh*intop3(shell.DA33)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("DD11_Xia", "c/lh*intop3(shell.DD11)", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("DD12_Xia", "intop3(shell.DD12/shell.DD11)*DD11_Xia", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("DD22_Xia", "1/(2*c)*(I2*intop3(shell.DA22)+I0c*intop3(shell.DD22))", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("DD33_Xia", "lh/c*intop3(shell.DD33)", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2")
         .label("Park \u7b49\u4eba\u5f97\u51fa\u7684\u89e3\u6790\u521a\u5ea6\u5206\u91cf\u3002");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("DA11_Park", "2*c/(I0c/intop3(shell.DA11)+I0s/(5/6*intop3(shell.DAs11))+I2/intop3(shell.DD11))", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("DA12_Park", "intop3(shell.DA12/shell.DA11)*DA11_Park", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("DA22_Park", "intop3(shell.DA12)*DA12_Park/intop3(shell.DA11)+lh/c*intop3((shell.DA11*shell.DA22-shell.DA12^2)/shell.DA11)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("DA33_Park", "c/lh*intop3(shell.DA33)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("DD11_Park", "c/lh*intop3(shell.DD11)", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("DD12_Park", "intop3(shell.DD12/shell.DD11)*DD11_Park", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("DD22_Park", "1/(2*c)*(I2*intop3(shell.DA22)+I0c*intop3(shell.DD22))", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var2")
         .set("DD33_Park", "lh/c*intop3(shell.DD33)", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3")
         .label("Ye \u7b49\u4eba\u5f97\u51fa\u7684\u89e3\u6790\u521a\u5ea6\u5206\u91cf\u3002");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3")
         .set("DA11_Ye", "EE*th^4/(12*(1-Nu^2)*Iy)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var3")
         .set("DA12_Ye", "Nu*DA11_Ye", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var3")
         .set("DA22_Ye", "EE*th*lh/(c)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var3")
         .set("DA33_Ye", "EE*th*c/(2*(1+Nu)*lh)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable("var3")
         .set("DD11_Ye", "EE*th^3*2*c/(12*(1-Nu^2)*2*lh)", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var3")
         .set("DD12_Ye", "Nu*DD11_Ye", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var3")
         .set("DD22_Ye", "EE*Iy", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var3")
         .set("DD33_Ye", "EE*th^3*lh/(24*(1+Nu)*c)", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4")
         .label("\u57fa\u4e8e\u53cd\u4f5c\u7528\u529b\u7684\u6570\u503c\u521a\u5ea6\u5206\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var4").loadFile("FILENAME");
    model.component("comp1").variable("var4")
         .set("DA11_R", "intop1(-shell.RFx/d)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DA12_R", "intop1(-shell.RFx/d)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DA21_R", "intop2(-shell.RFy/(2*c))", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c21 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DA22_R", "intop2(-shell.RFy/(2*c))", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DA33_R", "intop1(-shell.RFy/d)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DD11_R", "intop1(shell.RMy/d)*L", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DD12_R", "intop1(shell.RMy/d)*L", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DD21_R", "intop2(-shell.RMx/(2*c))*L", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c21 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DD22_R", "intop2(-shell.RMx/(2*c))*L", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var4")
         .set("DD33_R", "0.5*(intop1(shell.RMx/(d))+intop2(-shell.RMy/(2*c)))*L", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5")
         .label("\u57fa\u4e8e\u80fd\u91cf\u7b49\u6548\u7684\u6570\u503c\u521a\u5ea6\u5206\u91cf");

//    To import content from file, use:
//    model.component("comp1").variable("var5").loadFile("FILENAME");
    model.component("comp1").variable("var5")
         .set("DA11_E", "shell.Ws_tot/(d*c)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var5")
         .set("DA22_E", "shell.Ws_tot/(d*c)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var5")
         .set("DA33_E", "shell.Ws_tot/(d*c)", "\u7b49\u6548\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.component("comp1").variable("var5")
         .set("DD11_E", "shell.Ws_tot/(d*c)*L^2", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var5")
         .set("DD22_E", "shell.Ws_tot/(d*c)*L^2", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var5")
         .set("DD33_E", "shell.Ws_tot/(d*c)*L^2", "\u7b49\u6548\u5f2f\u66f2\u521a\u5ea6\u77e9\u9635\uff0c33 \u5206\u91cf");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"EE"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"Nu"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});

    model.component("comp1").physics("shell").feature("to1").set("d", "th");

    model.nodeGroup().create("grp1", "Physics", "shell");
    model.nodeGroup("grp1").label("\u8f7d\u8377\u5de5\u51b5 1\uff1aDA11 \u548c DA12");

    model.component("comp1").physics("shell").create("disp1", "Displacement1", 1);

    model.nodeGroup("grp1").add("disp1");

    model.component("comp1").physics("shell").feature("disp1").selection().named("geom1_unisel1");
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp1").setIndex("U0", "-c", 0);
    model.component("comp1").physics("shell").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp1").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp1").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").create("disp2", "Displacement1", 1);

    model.nodeGroup("grp1").add("disp2");

    model.component("comp1").physics("shell").feature("disp2").selection().named("geom1_unisel2");
    model.component("comp1").physics("shell").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp2").setIndex("U0", "c", 0);
    model.component("comp1").physics("shell").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp2").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp2").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").create("disp3", "Displacement1", 1);

    model.nodeGroup("grp1").add("disp3");

    model.component("comp1").physics("shell").feature("disp3").selection().named("geom1_unisel3");
    model.component("comp1").physics("shell").feature("disp3").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp3").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp3").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp4", "Displacement1", 1);

    model.nodeGroup("grp1").add("disp4");

    model.component("comp1").physics("shell").feature("disp4").selection().named("geom1_unisel4");
    model.component("comp1").physics("shell").feature("disp4").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp4").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp4").setIndex("FreeRotationAround", true, 1);

    model.nodeGroup().create("grp2", "Physics", "shell");

    model.component("comp1").physics("shell").create("disp5", "Displacement1", 1);

    model.nodeGroup("grp2").add("disp5");

    model.component("comp1").physics("shell").feature("disp5").selection().named("geom1_unisel1");
    model.component("comp1").physics("shell").feature("disp5").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp5").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp5").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp5").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").create("disp6", "Displacement1", 1);

    model.nodeGroup("grp2").add("disp6");

    model.component("comp1").physics("shell").feature("disp6").selection().named("geom1_unisel2");
    model.component("comp1").physics("shell").feature("disp6").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp6").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp6").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp6").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").create("disp7", "Displacement1", 1);

    model.nodeGroup("grp2").add("disp7");

    model.component("comp1").physics("shell").feature("disp7").selection().named("geom1_unisel3");
    model.component("comp1").physics("shell").feature("disp7").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp7").setIndex("U0", "-0.5*d", 1);
    model.component("comp1").physics("shell").feature("disp7").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp7").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp8", "Displacement1", 1);

    model.nodeGroup("grp2").add("disp8");

    model.component("comp1").physics("shell").feature("disp8").selection().named("geom1_unisel4");
    model.component("comp1").physics("shell").feature("disp8").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp8").setIndex("U0", "0.5*d", 1);
    model.component("comp1").physics("shell").feature("disp8").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp8").setIndex("FreeRotationAround", true, 1);

    model.nodeGroup().create("grp3", "Physics", "shell");

    model.component("comp1").physics("shell").create("disp9", "Displacement1", 1);

    model.nodeGroup("grp3").add("disp9");

    model.component("comp1").physics("shell").feature("disp9").selection().named("geom1_unisel1");
    model.component("comp1").physics("shell").feature("disp9").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp9").setIndex("U0", "-0.5*c", 1);
    model.component("comp1").physics("shell").feature("disp9").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp9").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp9").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp10", "Displacement1", 1);

    model.nodeGroup("grp3").add("disp10");

    model.component("comp1").physics("shell").feature("disp10").selection().named("geom1_unisel2");
    model.component("comp1").physics("shell").feature("disp10").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp10").setIndex("U0", "0.5*c", 1);
    model.component("comp1").physics("shell").feature("disp10").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp10").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp10").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp11", "Displacement1", 1);

    model.nodeGroup("grp3").add("disp11");

    model.component("comp1").physics("shell").feature("disp11").selection().named("geom1_unisel3");
    model.component("comp1").physics("shell").feature("disp11").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp11").setIndex("U0", "-0.25*d", 0);
    model.component("comp1").physics("shell").feature("disp11").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp11").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp11").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").create("disp12", "Displacement1", 1);

    model.nodeGroup("grp3").add("disp12");

    model.component("comp1").physics("shell").feature("disp12").selection().named("geom1_unisel4");
    model.component("comp1").physics("shell").feature("disp12").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp12").setIndex("U0", "0.25*d", 0);
    model.component("comp1").physics("shell").feature("disp12").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("shell").feature("disp12").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp12").setIndex("FreeRotationAround", true, 0);

    model.nodeGroup().create("grp4", "Physics", "shell");

    model.component("comp1").physics("shell").create("disp13", "Displacement1", 1);

    model.nodeGroup("grp4").add("disp13");

    model.component("comp1").physics("shell").feature("disp13").selection().named("geom1_unisel1");
    model.component("comp1").physics("shell").feature("disp13").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp13").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp13").set("THETA_0", new String[]{"-c/L", "0"});
    model.component("comp1").physics("shell").create("disp14", "Displacement1", 1);

    model.nodeGroup("grp4").add("disp14");

    model.component("comp1").physics("shell").feature("disp14").selection().named("geom1_unisel2");
    model.component("comp1").physics("shell").feature("disp14").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp14").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp14").set("THETA_0", new String[]{"-c/L", "0"});
    model.component("comp1").physics("shell").create("disp15", "Displacement1", 1);

    model.nodeGroup("grp4").add("disp15");

    model.component("comp1").physics("shell").feature("disp15").selection().named("geom1_unisel3");
    model.component("comp1").physics("shell").feature("disp15").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp15").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp15").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp16", "Displacement1", 1);

    model.nodeGroup("grp4").add("disp16");

    model.component("comp1").physics("shell").feature("disp16").selection().named("geom1_unisel4");
    model.component("comp1").physics("shell").feature("disp16").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp16").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp16").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp17", "Displacement0", 0);

    model.nodeGroup("grp4").add("disp17");

    model.component("comp1").physics("shell").feature("disp17").selection().named("geom1_unisel5");
    model.component("comp1").physics("shell").feature("disp17").setIndex("Direction", "prescribed", 2);

    model.nodeGroup().create("grp5", "Physics", "shell");

    model.component("comp1").physics("shell").create("disp18", "Displacement1", 1);

    model.nodeGroup("grp5").add("disp18");

    model.component("comp1").physics("shell").feature("disp18").selection().named("geom1_unisel1");
    model.component("comp1").physics("shell").feature("disp18").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp18").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp18").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp19", "Displacement1", 1);

    model.nodeGroup("grp5").add("disp19");

    model.component("comp1").physics("shell").feature("disp19").selection().named("geom1_unisel2");
    model.component("comp1").physics("shell").feature("disp19").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp19").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp19").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("shell").create("disp20", "Displacement1", 1);

    model.nodeGroup("grp5").add("disp20");

    model.component("comp1").physics("shell").feature("disp20").selection().named("geom1_unisel3");
    model.component("comp1").physics("shell").feature("disp20").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp20").setIndex("U0", "0.5*Z*d/L", 1);
    model.component("comp1").physics("shell").feature("disp20").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp20")
         .set("THETA_0", new String[]{"-0.5*d/L*shell.anZ", "0"});
    model.component("comp1").physics("shell").create("disp21", "Displacement1", 1);

    model.nodeGroup("grp5").add("disp21");

    model.component("comp1").physics("shell").feature("disp21").selection().named("geom1_unisel4");
    model.component("comp1").physics("shell").feature("disp21").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp21").setIndex("U0", "-0.5*Z*d/L", 1);
    model.component("comp1").physics("shell").feature("disp21").set("RotationType", "RotationGroup");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("shell").feature("disp21")
         .set("THETA_0", new String[]{"-0.5*d/L*shell.anZ", "0"});
    model.component("comp1").physics("shell").create("disp22", "Displacement0", 0);

    model.nodeGroup("grp5").add("disp22");

    model.component("comp1").physics("shell").feature("disp22").selection().named("geom1_unisel5");
    model.component("comp1").physics("shell").feature("disp22").setIndex("Direction", "prescribed", 2);

    model.nodeGroup().create("grp6", "Physics", "shell");

    model.component("comp1").physics("shell").create("disp23", "Displacement1", 1);

    model.nodeGroup("grp6").add("disp23");

    model.component("comp1").physics("shell").feature("disp23").selection().named("geom1_unisel1");
    model.component("comp1").physics("shell").feature("disp23").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp23").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp23").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp23").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").feature("disp23")
         .set("THETA_0", new String[]{"0", "0.5*c/L*shell.anZ"});
    model.component("comp1").physics("shell").create("disp24", "Displacement1", 1);

    model.nodeGroup("grp6").add("disp24");

    model.component("comp1").physics("shell").feature("disp24").selection().named("geom1_unisel2");
    model.component("comp1").physics("shell").feature("disp24").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp24").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("shell").feature("disp24").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp24").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").feature("disp24")
         .set("THETA_0", new String[]{"0", "0.5*c/L*shell.anZ"});
    model.component("comp1").physics("shell").create("disp25", "Displacement1", 1);

    model.nodeGroup("grp6").add("disp25");

    model.component("comp1").physics("shell").feature("disp25").selection().named("geom1_unisel3");
    model.component("comp1").physics("shell").feature("disp25").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp25").setIndex("U0", "-0.25*Z*d/L", 0);
    model.component("comp1").physics("shell").feature("disp25").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp25").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").feature("disp25").set("THETA_0", new String[]{"0", "-0.25*d/L"});
    model.component("comp1").physics("shell").create("disp26", "Displacement1", 1);

    model.nodeGroup("grp6").add("disp26");

    model.component("comp1").physics("shell").feature("disp26").selection().named("geom1_unisel4");
    model.component("comp1").physics("shell").feature("disp26").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("shell").feature("disp26").setIndex("U0", "0.25*Z*d/L", 0);
    model.component("comp1").physics("shell").feature("disp26").set("RotationType", "RotationGroup");
    model.component("comp1").physics("shell").feature("disp26").setIndex("FreeRotationAround", true, 0);
    model.component("comp1").physics("shell").feature("disp26").set("THETA_0", new String[]{"0", "-0.25*d/L"});
    model.component("comp1").physics("shell").create("disp27", "Displacement0", 0);

    model.nodeGroup("grp6").add("disp27");

    model.component("comp1").physics("shell").feature("disp27").selection().named("geom1_unisel5");
    model.component("comp1").physics("shell").feature("disp27").setIndex("Direction", "prescribed", 2);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("geom1_unisel3");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run("map1");

    model.study("std1").label("\u7814\u7a76\uff1a\u8f7d\u8377\u5de5\u51b5 1");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "switch");
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param").setIndex("switchname", "par2", 0);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"shell/disp5", "shell/disp6", "shell/disp7", "shell/disp8", "shell/disp9", "shell/disp10", "shell/disp11", "shell/disp12", "shell/disp13", "shell/disp14", 
         "shell/disp15", "shell/disp16", "shell/disp17", "shell/disp18", "shell/disp19", "shell/disp20", "shell/disp21", "shell/disp22", "shell/disp23", "shell/disp24", 
         "shell/disp25", "shell/disp26", "shell/disp27"});
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").set("sweeptype", "switch");
    model.study("std2").feature("param").setIndex("switchname", "default", 0);
    model.study("std2").feature("param").setIndex("switchcase", "all", 0);
    model.study("std2").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std2").feature("param").setIndex("switchname", "default", 0);
    model.study("std2").feature("param").setIndex("switchcase", "all", 0);
    model.study("std2").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std2").feature("param").setIndex("switchname", "par2", 0);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"shell/disp1", "shell/disp2", "shell/disp3", "shell/disp4", "shell/disp9", "shell/disp10", "shell/disp11", "shell/disp12", "shell/disp13", "shell/disp14", 
         "shell/disp15", "shell/disp16", "shell/disp17", "shell/disp18", "shell/disp19", "shell/disp20", "shell/disp21", "shell/disp22", "shell/disp23", "shell/disp24", 
         "shell/disp25", "shell/disp26", "shell/disp27"});
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol6");
    model.sol("sol6").study("std2");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol6");
    model.batch("p2").run("compute");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std3").setGenPlots(false);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").set("sweeptype", "switch");
    model.study("std3").feature("param").setIndex("switchname", "default", 0);
    model.study("std3").feature("param").setIndex("switchcase", "all", 0);
    model.study("std3").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std3").feature("param").setIndex("switchname", "default", 0);
    model.study("std3").feature("param").setIndex("switchcase", "all", 0);
    model.study("std3").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std3").feature("param").setIndex("switchname", "par2", 0);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat")
         .set("disabledphysics", new String[]{"shell/disp1", "shell/disp2", "shell/disp3", "shell/disp4", "shell/disp5", "shell/disp6", "shell/disp7", "shell/disp8", "shell/disp13", "shell/disp14", 
         "shell/disp15", "shell/disp16", "shell/disp17", "shell/disp18", "shell/disp19", "shell/disp20", "shell/disp21", "shell/disp22", "shell/disp23", "shell/disp24", 
         "shell/disp25", "shell/disp26", "shell/disp27"});
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol10");
    model.sol("sol10").study("std3");
    model.sol("sol10").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol10");
    model.batch("p3").run("compute");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std4").setGenPlots(false);
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").set("sweeptype", "switch");
    model.study("std4").feature("param").setIndex("switchname", "default", 0);
    model.study("std4").feature("param").setIndex("switchcase", "all", 0);
    model.study("std4").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std4").feature("param").setIndex("switchname", "default", 0);
    model.study("std4").feature("param").setIndex("switchcase", "all", 0);
    model.study("std4").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std4").feature("param").setIndex("switchname", "par2", 0);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat")
         .set("disabledphysics", new String[]{"shell/disp1", "shell/disp2", "shell/disp3", "shell/disp4", "shell/disp5", "shell/disp6", "shell/disp7", "shell/disp8", "shell/disp9", "shell/disp10", 
         "shell/disp11", "shell/disp12", "shell/disp18", "shell/disp19", "shell/disp20", "shell/disp21", "shell/disp22", "shell/disp23", "shell/disp24", "shell/disp25", 
         "shell/disp26", "shell/disp27"});
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol14");
    model.sol("sol14").study("std4");
    model.sol("sol14").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("p4").feature("so1").set("psol", "sol14");
    model.batch("p4").run("compute");

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std5").setGenPlots(false);
    model.study("std5").create("param", "Parametric");
    model.study("std5").feature("param").set("sweeptype", "switch");
    model.study("std5").feature("param").setIndex("switchname", "default", 0);
    model.study("std5").feature("param").setIndex("switchcase", "all", 0);
    model.study("std5").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std5").feature("param").setIndex("switchname", "default", 0);
    model.study("std5").feature("param").setIndex("switchcase", "all", 0);
    model.study("std5").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std5").feature("param").setIndex("switchname", "par2", 0);
    model.study("std5").feature("stat").set("useadvanceddisable", true);
    model.study("std5").feature("stat")
         .set("disabledphysics", new String[]{"shell/disp1", "shell/disp2", "shell/disp3", "shell/disp4", "shell/disp5", "shell/disp6", "shell/disp7", "shell/disp8", "shell/disp9", "shell/disp10", 
         "shell/disp11", "shell/disp12", "shell/disp13", "shell/disp14", "shell/disp15", "shell/disp16", "shell/disp17", "shell/disp23", "shell/disp24", "shell/disp25", 
         "shell/disp26", "shell/disp27"});
    model.study("std5").createAutoSequences("all");

    model.sol().create("sol18");
    model.sol("sol18").study("std5");
    model.sol("sol18").label("\u53c2\u6570\u5316\u89e3 5");

    model.batch("p5").feature("so1").set("psol", "sol18");
    model.batch("p5").run("compute");

    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std6").setGenPlots(false);
    model.study("std6").create("param", "Parametric");
    model.study("std6").feature("param").set("sweeptype", "switch");
    model.study("std6").feature("param").setIndex("switchname", "default", 0);
    model.study("std6").feature("param").setIndex("switchcase", "all", 0);
    model.study("std6").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std6").feature("param").setIndex("switchname", "default", 0);
    model.study("std6").feature("param").setIndex("switchcase", "all", 0);
    model.study("std6").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std6").feature("param").setIndex("switchname", "par2", 0);
    model.study("std6").feature("stat").set("useadvanceddisable", true);
    model.study("std6").feature("stat")
         .set("disabledphysics", new String[]{"shell/disp1", "shell/disp2", "shell/disp3", "shell/disp4", "shell/disp5", "shell/disp6", "shell/disp7", "shell/disp8", "shell/disp9", "shell/disp10", 
         "shell/disp11", "shell/disp12", "shell/disp13", "shell/disp14", "shell/disp15", "shell/disp16", "shell/disp17", "shell/disp18", "shell/disp19", "shell/disp20", 
         "shell/disp21", "shell/disp22"});
    model.study("std6").createAutoSequences("all");

    model.sol().create("sol22");
    model.sol("sol22").study("std6");
    model.sol("sol22").label("\u53c2\u6570\u5316\u89e3 6");

    model.batch("p6").feature("so1").set("psol", "sol22");
    model.batch("p6").run("compute");

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"planeangle", "\u5e73\u9762\u89d2", "rad", "rad"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e73\u79fb\u8f7d\u8377\u5de5\u51b5\u7684\u4f4d\u79fb");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 0.15);
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset4");
    model.result("pg1").feature("surf2").setIndex("looplevel", 1, 0);
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature("surf3").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("data", "dset6");
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("arraydim", "1");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "c", 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-0.5*d", 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u8f7d\u8377\u5de5\u51b5 1", 0, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "3.5*c", 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-0.5*d", 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u8f7d\u8377\u5de5\u51b5 2", 1, 3);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "6*c", 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "-0.5*d", 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u8f7d\u8377\u5de5\u51b5 3", 2, 3);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").feature("tlan1").set("anchorpoint", "uppermiddle");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u65cb\u8f6c\u8f7d\u8377\u5de5\u51b5\u7684\u603b\u65cb\u8f6c");
    model.result("pg2").set("data", "dset8");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "shell.totrot");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scale", 10);
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("data", "dset10");
    model.result("pg2").feature("surf2").set("expr", "shell.totrot");
    model.result("pg2").feature("surf3").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("data", "dset12");
    model.result("pg2").feature("surf3").set("expr", "shell.totrot");
    model.result("pg2").feature("tlan1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u8f7d\u8377\u5de5\u51b5 4", 0, 3);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u8f7d\u8377\u5de5\u51b5 5", 1, 3);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u8f7d\u8377\u5de5\u51b5 6", 2, 3);
    model.result("pg2").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("Xia \u7b49\u4eba\u5f97\u51fa\u7684\u89e3\u6790\u62c9\u4f38\u521a\u5ea6\u77e9\u9635\u3002");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "manual", 0);
    model.result().evaluationGroup("eg1").setIndex("looplevel", new int[]{1}, 0);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "DA11_Xia", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "DA12_Xia", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "DA22_Xia", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "DA33_Xia", 3);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "DD11_Xia", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "DD12_Xia", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "DD22_Xia", 2);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "DD33_Xia", 3);
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "DA11_Park", 0);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "DA12_Park", 1);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "DA22_Park", 2);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "DA33_Park", 3);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg3");
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", "DD11_Park", 0);
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", "DD12_Park", 1);
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", "DD22_Park", 2);
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", "DD33_Park", 3);
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().duplicate("eg5", "eg4");
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("expr", "DA11_Ye", 0);
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("expr", "DA12_Ye", 1);
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("expr", "DA22_Ye", 2);
    model.result().evaluationGroup("eg5").feature("gev1").setIndex("expr", "DA33_Ye", 3);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().duplicate("eg6", "eg5");
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("expr", "DD11_Ye", 0);
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("expr", "DD12_Ye", 1);
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("expr", "DD22_Ye", 2);
    model.result().evaluationGroup("eg6").feature("gev1").setIndex("expr", "DD33_Ye", 3);
    model.result().evaluationGroup("eg6").run();
    model.result().evaluationGroup().create("eg7", "EvaluationGroup");
    model.result().evaluationGroup("eg7")
         .label("\u57fa\u4e8e\u53cd\u4f5c\u7528\u529b\u7684\u6570\u503c\u62c9\u4f38\u521a\u5ea6\u77e9\u9635");
    model.result().evaluationGroup("eg7").set("data", "dset2");
    model.result().evaluationGroup("eg7").setIndex("looplevelinput", "manual", 0);
    model.result().evaluationGroup("eg7").setIndex("looplevel", new int[]{1}, 0);
    model.result().evaluationGroup("eg7").set("includeparameters", false);
    model.result().evaluationGroup("eg7").set("transpose", true);
    model.result().evaluationGroup("eg7").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg7").feature("gev1").setIndex("expr", "DA11_R", 0);
    model.result().evaluationGroup("eg7").feature("gev1").setIndex("expr", "DA21_R", 1);
    model.result().evaluationGroup("eg7").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg7").feature("gev2").set("data", "dset4");
    model.result().evaluationGroup("eg7").feature("gev2").setIndex("looplevelinput", "manual", 0);
    model.result().evaluationGroup("eg7").feature("gev2").setIndex("looplevel", new int[]{1}, 0);
    model.result().evaluationGroup("eg7").feature("gev2").setIndex("expr", "DA12_R", 0);
    model.result().evaluationGroup("eg7").feature("gev2").setIndex("expr", "DA22_R", 1);
    model.result().evaluationGroup("eg7").create("gev3", "EvalGlobal");
    model.result().evaluationGroup("eg7").feature("gev3").set("data", "dset6");
    model.result().evaluationGroup("eg7").feature("gev3").setIndex("looplevelinput", "manual", 0);
    model.result().evaluationGroup("eg7").feature("gev3").setIndex("looplevel", new int[]{1}, 0);
    model.result().evaluationGroup("eg7").feature("gev3").setIndex("expr", "DA33_R", 0);
    model.result().evaluationGroup("eg7").run();
    model.result().evaluationGroup().duplicate("eg8", "eg7");
    model.result().evaluationGroup("eg8").set("data", "dset8");
    model.result().evaluationGroup("eg8").feature("gev1").setIndex("expr", "DD11_R", 0);
    model.result().evaluationGroup("eg8").feature("gev1").setIndex("expr", "DD21_R", 1);
    model.result().evaluationGroup("eg8").feature("gev2").set("data", "dset10");
    model.result().evaluationGroup("eg8").feature("gev2").setIndex("expr", "DD12_R", 0);
    model.result().evaluationGroup("eg8").feature("gev2").setIndex("expr", "DD22_R", 1);
    model.result().evaluationGroup("eg8").feature("gev3").set("data", "dset12");
    model.result().evaluationGroup("eg8").feature("gev3").setIndex("expr", "DD33_R", 0);
    model.result().evaluationGroup("eg8").run();
    model.result().evaluationGroup().duplicate("eg9", "eg7");
    model.result().evaluationGroup("eg9").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg9").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg9").feature("gev1").setIndex("expr", "DA11_E", 0);
    model.result().evaluationGroup("eg9").feature("gev2").set("expr", new String[]{});
    model.result().evaluationGroup("eg9").feature("gev2").set("descr", new String[]{});
    model.result().evaluationGroup("eg9").feature("gev2").setIndex("expr", "DA22_E", 0);
    model.result().evaluationGroup("eg9").feature("gev3").set("expr", new String[]{});
    model.result().evaluationGroup("eg9").feature("gev3").set("descr", new String[]{});
    model.result().evaluationGroup("eg9").feature("gev3").setIndex("expr", "DA33_E", 0);
    model.result().evaluationGroup("eg9").run();
    model.result().evaluationGroup().duplicate("eg10", "eg9");
    model.result().evaluationGroup("eg10").set("data", "dset8");
    model.result().evaluationGroup("eg10").feature("gev1").setIndex("expr", "DD11_E", 0);
    model.result().evaluationGroup("eg10").feature("gev2").set("data", "dset10");
    model.result().evaluationGroup("eg10").feature("gev2").setIndex("expr", "DD22_E", 0);
    model.result().evaluationGroup("eg10").feature("gev3").set("data", "dset12");
    model.result().evaluationGroup("eg10").feature("gev3").setIndex("expr", "DD33_E", 0);
    model.result().evaluationGroup("eg10").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp7", "Results");
    model.nodeGroup("grp7").add("plotgroup", "pg1");
    model.nodeGroup("grp7").add("plotgroup", "pg2");
    model.nodeGroup("grp7").add("evaluationgroup", "eg1");
    model.nodeGroup("grp7").add("evaluationgroup", "eg2");
    model.nodeGroup("grp7").add("evaluationgroup", "eg3");
    model.nodeGroup("grp7").add("evaluationgroup", "eg4");
    model.nodeGroup("grp7").add("evaluationgroup", "eg5");
    model.nodeGroup("grp7").add("evaluationgroup", "eg6");
    model.nodeGroup("grp7").add("evaluationgroup", "eg7");
    model.nodeGroup("grp7").add("evaluationgroup", "eg8");
    model.nodeGroup("grp7").add("evaluationgroup", "eg9");
    model.nodeGroup("grp7").add("evaluationgroup", "eg10");
    model.nodeGroup("grp7").label("\u68af\u5f62\u6ce2\u7eb9");
    model.nodeGroup().duplicate("grp8", "grp7");
    model.nodeGroup("grp8").label("\u5706\u5f62\u6ce2\u7eb9");

    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("surf3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").setIndex("looplevel", 2, 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").setIndex("looplevel", 2, 0);
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().evaluationGroup("eg11").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg11").run();
    model.result().evaluationGroup("eg12").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg12").run();
    model.result().evaluationGroup("eg13").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg13").run();
    model.result().evaluationGroup("eg14").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg14").run();
    model.result().evaluationGroup("eg15").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg15").run();
    model.result().evaluationGroup("eg16").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg16").run();
    model.result().evaluationGroup("eg17").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg17").feature("gev2").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg17").feature("gev3").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg17").run();
    model.result().evaluationGroup("eg18").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg18").feature("gev2").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg18").feature("gev3").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg18").run();
    model.result().evaluationGroup("eg19").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg19").feature("gev2").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg19").feature("gev3").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg19").run();
    model.result().evaluationGroup("eg20").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg20").feature("gev2").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg20").feature("gev3").setIndex("looplevel", new int[]{2}, 0);
    model.result().evaluationGroup("eg20").run();
    model.result("pg3").run();

    model.title("\u6ce2\u7eb9\u677f\u7684\u5747\u8d28\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u57fa\u4e8e\u57fa\u672c\u5355\u5143\u7684\u6ce2\u7eb9\u677f\u7684\u5747\u5300\u6570\u503c\u6a21\u578b\uff0c\u5e76\u5c06\u901a\u8fc7\u6570\u503c\u65b9\u6cd5\u83b7\u5f97\u7684\u7b49\u6548\u521a\u5ea6\u77e9\u9635\u4e0e\u5404\u79cd\u5206\u6790\u6a21\u578b\u8fdb\u884c\u6bd4\u8f83\u3002\u5176\u4e2d\u6db5\u76d6\u4e86\u68af\u5f62\u548c\u5706\u5f62\u4e24\u79cd\u6ce2\u7eb9\u578b\u6750\u3002");

    return model;
  }

  public static Model run3(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();

    model.label("corrugated_sheet.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
