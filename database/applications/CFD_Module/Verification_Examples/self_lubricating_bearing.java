/*
 * self_lubricating_bearing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class self_lubricating_bearing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("tff", "ThinFilmFlowShell", "geom1");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");

    model.component("comp1").multiphysics().create("tfpf1", "ThinFilmAndPorousMediaFlow", 2);
    model.component("comp1").multiphysics("tfpf1").set("ThinFilmFluid_physics", "tff");
    model.component("comp1").multiphysics("tfpf1").set("PorousMedia_physics", "dl");
    model.component("comp1").multiphysics("tfpf1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tff", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/tfpf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R", "L", "\u8f74\u9888\u534a\u5f84");
    model.param().set("L", "H/0.2", "\u8f74\u5957\u957f\u5ea6");
    model.param().set("U", "1[m/s]", "\u8f74\u9888\u5207\u5411\u901f\u5ea6");
    model.param().set("H", "Psi*C^3/Phi", "\u591a\u5b54\u886c\u5957\u539a\u5ea6");
    model.param().set("C", "1e-5[m]/Psi", "\u540c\u5fc3\u95f4\u9699");
    model.param().set("ep", "0.9", "\u8f74\u9888\u79bb\u5fc3\u7387");
    model.param().set("alpha", "0.1", "\u6ed1\u79fb\u7cfb\u6570");
    model.param().set("s", "0.25", "\u6ed1\u79fb\u53c2\u6570");
    model.param().set("Phi", "(s*alpha*C)^2", "\u591a\u5b54\u886c\u5957\u7684\u6e17\u900f\u7387");
    model.param().set("Psi", "0.001", "\u6e17\u900f\u7387\u53c2\u6570");
    model.param().set("n_upper_lim", "10", "\u5085\u91cc\u53f6\u7ea7\u6570\u4e0a\u9650");
    model.param().set("rho", "1[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu", "1[Pa*s]", "\u6d41\u4f53\u9ecf\u5ea6");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("theta", "atan2(y,x)[rad]", "\u6cbf\u5706\u5468\u7684\u89d2\u5ea6");
    model.component("comp1").variable("var1")
         .set("h_f", "C*(1+ep*cos(theta))", "\u6da6\u6ed1\u6cb9\u819c\u539a\u5ea6");
    model.component("comp1").variable("var1")
         .set("u_b", "-U*sin(theta)", "\u8f74\u9888\u901f\u5ea6\u7684 X \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("v_b", "U*cos(theta)", "\u8f74\u9888\u901f\u5ea6\u7684 Y \u5206\u91cf");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "beta_n");
    model.component("comp1").func("an1").set("expr", "2*n-1");
    model.component("comp1").func("an1").set("args", "n");
    model.component("comp1").func("an1").setIndex("plotargs", "n_upper_lim", 0, 2);
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").set("funcname", "g_n");
    model.component("comp1").func("an2")
         .set("expr", "sin(theta)*(2*s^2+2*s*(1+ep*cos(theta))+(1+ep*cos(theta))^2)/((s+1+ep*cos(theta))*(((6*(alpha^2)*(s^2)+4*s*(1+ep*cos(theta))+(1+ep*cos(theta))^2)*(1+ep*cos(theta))^2)+(12*Psi*(s+1+ep*cos(theta))*tanh(pi*beta_n(n)*H/L)/(pi*beta_n(n)*H/L))))");
    model.component("comp1").func("an2").set("args", "ep, n, Psi, s, theta");
    model.component("comp1").func("an2").setIndex("plotaxis", false, 1);
    model.component("comp1").func("an2").setIndex("plotaxis", true, 3);
    model.component("comp1").func().create("an3", "Analytic");
    model.component("comp1").func("an3").set("funcname", "pbar_summand");
    model.component("comp1").func("an3")
         .set("expr", "(-1)^(n+1)*ep*g_n(ep,n,Psi,s,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3");
    model.component("comp1").func("an3").set("args", "ep, n, Psi, s, theta, z");
    model.component("comp1").func("an3").setIndex("plotaxis", false, 1);
    model.component("comp1").func("an3").setIndex("plotaxis", true, 3);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R+H");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-L/2"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "H", 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("tff").selection().set(8, 9, 14, 16);
    model.component("comp1").physics("tff").prop("ReferencePressure").set("pref", 0);
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "h_f");
    model.component("comp1").physics("tff").feature("ffp1").set("TangentialBaseVelocity", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("vb", new String[]{"u_b", "v_b", "0"});
    model.component("comp1").physics("tff").feature("ffp1").set("mure_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("mure", "mu");
    model.component("comp1").physics("tff").feature("ffp1").set("rho_mat", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("rho", "rho");
    model.component("comp1").physics("dl").prop("PhysicalModelProperty").set("pref", 0);
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho", "rho");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu", "mu");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"Phi", "0", "0", "0", "Phi", "0", "0", "0", "Phi"});
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 2);
    model.component("comp1").physics("dl").feature("pr1").selection().set(4, 5, 6, 7, 12, 13, 17, 18);

    model.component("comp1").multiphysics("tfpf1").set("couplingType", "BeaversAndJoseph");
    model.component("comp1").multiphysics("tfpf1").set("alphaBJw", "alpha");
    model.component("comp1").multiphysics("tfpf1").set("alphaBJb", "alpha");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3, 4, 9, 10, 15, 19, 23, 27);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("equidistant", true);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(4, 5, 12, 17);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 14, 22, 30);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("ftet1").active(false);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(9, 16);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(8, 9, 14, 16);

    model.param().set("R", "20*L");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "R", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.25 0.5", 0);
    model.study("std1").feature("param").setIndex("pname", "R", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "R", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "Psi", 1);
    model.study("std1").feature("param").setIndex("plistarr", "1 0.1 0.01 0.001", 1);
    model.study("std1").feature("param").setIndex("pname", "R", 2);
    model.study("std1").feature("param").setIndex("plistarr", "", 2);
    model.study("std1").feature("param").setIndex("punit", "m", 2);
    model.study("std1").feature("param").setIndex("pname", "R", 2);
    model.study("std1").feature("param").setIndex("plistarr", "", 2);
    model.study("std1").feature("param").setIndex("punit", "m", 2);
    model.study("std1").feature("param").setIndex("pname", "ep", 2);
    model.study("std1").feature("param").setIndex("plistarr", "0.1 0.3 0.5 0.7 0.9", 2);
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.param().set("R", "L");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/tff", true);
    model.study("std2").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/tfpf1", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol43").runAll();

    model.result().setOnlyPlotWhenRequested(true);
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().dataset("grid1").set("par1", "ep");
    model.result().dataset().duplicate("grid2", "grid1");
    model.result().dataset("grid2").set("par1", "Psi");
    model.result().dataset("grid2").set("parmin1", 0.001);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("looplevelinput", "manual", 2);
    model.result().numerical("gev1").setIndex("looplevel", new int[]{1}, 2);
    model.result().numerical("gev1").setIndex("looplevelinput", "manual", 1);
    model.result().numerical("gev1").setIndex("looplevel", new int[]{1}, 1);
    model.result().numerical("gev1")
         .setIndex("expr", "(C^2/(mu*U*L^3))*sqrt((intop1(pfilm*cos(theta)))^2+(intop1(pfilm*sin(theta)))^2)", 0);
    model.result().numerical("gev1")
         .setIndex("expr", "atan(-(intop1(pfilm*sin(theta)))/(intop1(pfilm*cos(theta))))", 1);
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").setIndex("looplevel", new int[]{2}, 1);
    model.result().numerical().duplicate("gev3", "gev2");
    model.result().numerical("gev3").setIndex("looplevel", new int[]{3}, 1);
    model.result().numerical().duplicate("gev4", "gev3");
    model.result().numerical("gev4").setIndex("looplevel", new int[]{4}, 1);
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").set("data", "dset2");
    model.result().numerical("gev5").setIndex("looplevelinput", "manual", 2);
    model.result().numerical("gev5").setIndex("looplevel", new int[]{1}, 2);
    model.result().numerical("gev5").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev5").setIndex("looplevel", new int[]{1}, 0);
    model.result().numerical("gev5")
         .setIndex("expr", "(C^2/(mu*U*L^3))*sqrt((intop1(pfilm*cos(theta)))^2+(intop1(pfilm*sin(theta)))^2)", 0);
    model.result().numerical().duplicate("gev6", "gev5");
    model.result().numerical("gev6").setIndex("looplevel", new int[]{2}, 0);
    model.result().numerical().duplicate("gev7", "gev6");
    model.result().numerical("gev7").setIndex("looplevel", new int[]{3}, 0);
    model.result().numerical().duplicate("gev8", "gev7");
    model.result().numerical("gev8").setIndex("looplevel", new int[]{4}, 0);
    model.result().numerical().duplicate("gev9", "gev8");
    model.result().numerical("gev9").setIndex("looplevel", new int[]{5}, 0);
    model.result().numerical().create("gev10", "EvalGlobal");
    model.result().numerical("gev10").set("data", "dset2");
    model.result().numerical("gev10").setIndex("looplevelinput", "manual", 2);
    model.result().numerical("gev10").setIndex("looplevel", new int[]{2}, 2);
    model.result().numerical("gev10").setIndex("looplevelinput", "manual", 1);
    model.result().numerical("gev10").setIndex("looplevel", new int[]{1}, 1);
    model.result().numerical("gev10")
         .setIndex("expr", "(C^2/(mu*U*L^3))*sqrt((intop1(pfilm*cos(theta)))^2+(intop1(pfilm*sin(theta)))^2)", 0);
    model.result().numerical().duplicate("gev11", "gev10");
    model.result().numerical("gev11").setIndex("looplevel", new int[]{2}, 1);
    model.result().numerical().duplicate("gev12", "gev11");
    model.result().numerical("gev12").setIndex("looplevel", new int[]{3}, 1);
    model.result().numerical().duplicate("gev13", "gev12");
    model.result().numerical("gev13").setIndex("looplevel", new int[]{4}, 1);
    model.result().numerical().create("gev14", "EvalGlobal");
    model.result().numerical("gev14").set("data", "dset2");
    model.result().numerical("gev14").setIndex("looplevelinput", "manual", 2);
    model.result().numerical("gev14").setIndex("looplevel", new int[]{2}, 2);
    model.result().numerical("gev14").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev14").setIndex("looplevel", new int[]{1}, 0);
    model.result().numerical("gev14")
         .setIndex("expr", "(C^2/(mu*U*L^3))*sqrt((intop1(pfilm*cos(theta)))^2+(intop1(pfilm*sin(theta)))^2)", 0);
    model.result().numerical().duplicate("gev15", "gev14");
    model.result().numerical("gev15").setIndex("looplevel", new int[]{2}, 0);
    model.result().numerical().duplicate("gev16", "gev15");
    model.result().numerical("gev16").setIndex("looplevel", new int[]{3}, 0);
    model.result().numerical().duplicate("gev17", "gev16");
    model.result().numerical("gev17").setIndex("looplevel", new int[]{4}, 0);
    model.result().numerical().duplicate("gev18", "gev17");
    model.result().numerical("gev18").setIndex("looplevel", new int[]{5}, 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u5168\u5c40\u8ba1\u7b97 4");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").setResult();
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5168\u5c40\u8ba1\u7b97 5");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").setResult();
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u5168\u5c40\u8ba1\u7b97 6");
    model.result().numerical("gev6").set("table", "tbl6");
    model.result().numerical("gev6").setResult();
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("\u5168\u5c40\u8ba1\u7b97 7");
    model.result().numerical("gev7").set("table", "tbl7");
    model.result().numerical("gev7").setResult();
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8").comments("\u5168\u5c40\u8ba1\u7b97 8");
    model.result().numerical("gev8").set("table", "tbl8");
    model.result().numerical("gev8").setResult();
    model.result().table().create("tbl9", "Table");
    model.result().table("tbl9").comments("\u5168\u5c40\u8ba1\u7b97 9");
    model.result().numerical("gev9").set("table", "tbl9");
    model.result().numerical("gev9").setResult();
    model.result().table().create("tbl10", "Table");
    model.result().table("tbl10").comments("\u5168\u5c40\u8ba1\u7b97 10");
    model.result().numerical("gev10").set("table", "tbl10");
    model.result().numerical("gev10").setResult();
    model.result().table().create("tbl11", "Table");
    model.result().table("tbl11").comments("\u5168\u5c40\u8ba1\u7b97 11");
    model.result().numerical("gev11").set("table", "tbl11");
    model.result().numerical("gev11").setResult();
    model.result().table().create("tbl12", "Table");
    model.result().table("tbl12").comments("\u5168\u5c40\u8ba1\u7b97 12");
    model.result().numerical("gev12").set("table", "tbl12");
    model.result().numerical("gev12").setResult();
    model.result().table().create("tbl13", "Table");
    model.result().table("tbl13").comments("\u5168\u5c40\u8ba1\u7b97 13");
    model.result().numerical("gev13").set("table", "tbl13");
    model.result().numerical("gev13").setResult();
    model.result().table().create("tbl14", "Table");
    model.result().table("tbl14").comments("\u5168\u5c40\u8ba1\u7b97 14");
    model.result().numerical("gev14").set("table", "tbl14");
    model.result().numerical("gev14").setResult();
    model.result().table().create("tbl15", "Table");
    model.result().table("tbl15").comments("\u5168\u5c40\u8ba1\u7b97 15");
    model.result().numerical("gev15").set("table", "tbl15");
    model.result().numerical("gev15").setResult();
    model.result().table().create("tbl16", "Table");
    model.result().table("tbl16").comments("\u5168\u5c40\u8ba1\u7b97 16");
    model.result().numerical("gev16").set("table", "tbl16");
    model.result().numerical("gev16").setResult();
    model.result().table().create("tbl17", "Table");
    model.result().table("tbl17").comments("\u5168\u5c40\u8ba1\u7b97 17");
    model.result().numerical("gev17").set("table", "tbl17");
    model.result().numerical("gev17").setResult();
    model.result().table().create("tbl18", "Table");
    model.result().table("tbl18").comments("\u5168\u5c40\u8ba1\u7b97 18");
    model.result().numerical("gev18").set("table", "tbl18");
    model.result().numerical("gev18").setResult();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u65e0\u91cf\u7eb2\u627f\u8f7d vs. ep\uff1as=0.25");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u4e0d\u540c \\Psi \u7684\u65e0\u91cf\u7eb2\u627f\u8f7d");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\\epsilon");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "W_nd");
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").create("fun1", "Function");
    model.result("pg1").feature("fun1").set("linewidth", "preference");
    model.result("pg1").feature("fun1").set("data", "grid1");
    model.result("pg1").feature("fun1")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").feature("fun1").set("xdataexpr", "ep");
    model.result("pg1").feature("fun1").set("linecolor", "cyclereset");
    model.result("pg1").feature("fun1").set("legend", true);
    model.result("pg1").feature("fun1").set("legendmethod", "manual");
    model.result("pg1").feature("fun1").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=1", 0);
    model.result("pg1").feature().duplicate("fun2", "fun1");
    model.result("pg1").feature("fun2")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").feature("fun2").set("linecolor", "cycle");
    model.result("pg1").feature("fun2").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.1", 0);
    model.result("pg1").feature().duplicate("fun3", "fun2");
    model.result("pg1").feature("fun3")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").feature("fun3").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.01", 0);
    model.result("pg1").feature().duplicate("fun4", "fun3");
    model.result("pg1").feature("fun4")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").feature("fun4").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.001", 0);
    model.result("pg1").feature().duplicate("fun5", "fun4");
    model.result("pg1").feature("fun5")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").feature("fun5").set("linestyle", "dashed");
    model.result("pg1").feature("fun5").set("linecolor", "cyclereset");
    model.result("pg1").feature("fun5").set("legend", false);
    model.result("pg1").feature().duplicate("fun6", "fun5");
    model.result("pg1").feature("fun6")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").feature("fun6").set("linecolor", "cycle");
    model.result("pg1").feature().duplicate("fun7", "fun6");
    model.result("pg1").feature("fun7")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").feature().duplicate("fun8", "fun7");
    model.result("pg1").feature("fun8")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("xaxisdata", 3);
    model.result("pg1").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg1").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg1").feature("tblp1").set("linemarker", "cyclereset");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6570\u503c\uff0c\\Psi=1", 0);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").feature("tblp2").set("table", "tbl2");
    model.result("pg1").feature("tblp2").set("linecolor", "cycle");
    model.result("pg1").feature("tblp2").set("linemarker", "cycle");
    model.result("pg1").feature("tblp2").setIndex("legends", "\u6570\u503c\uff0c\\Psi=0.1", 0);
    model.result("pg1").feature().duplicate("tblp3", "tblp2");
    model.result("pg1").feature("tblp3").set("table", "tbl3");
    model.result("pg1").feature("tblp3").setIndex("legends", "\u6570\u503c\uff0c\\Psi=0.01", 0);
    model.result("pg1").feature().duplicate("tblp4", "tblp3");
    model.result("pg1").feature("tblp4").set("table", "tbl4");
    model.result("pg1").feature("tblp4").setIndex("legends", "\u6570\u503c\uff0c\\Psi=0.001", 0);
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("data", "dset1");
    model.result("pg1").feature("ann1")
         .set("text", "\\unicode{\u865a\u7ebf\uff1a}s=0\\\\\\unicode{\u5b9e\u7ebf\uff1a}s=0.25");
    model.result("pg1").feature("ann1").set("latexmarkup", true);
    model.result("pg1").feature("ann1").set("posxexpr", 0.1);
    model.result("pg1").feature("ann1").set("posyexpr", 5);
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("showframe", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u65e0\u91cf\u7eb2\u627f\u8f7d vs. Psi\uff1as=0.25");
    model.result("pg2").set("data", "none");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u4e0d\u540c \\epsilon \u7684\u65e0\u91cf\u7eb2\u627f\u8f7d");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\\Psi");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "W_nd");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").create("fun1", "Function");
    model.result("pg2").feature("fun1").set("linewidth", "preference");
    model.result("pg2").feature("fun1").set("data", "grid2");
    model.result("pg2").feature("fun1")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.1,n,Psi,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.1,n,Psi,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature("fun1").set("xdataexpr", "Psi");
    model.result("pg2").feature("fun1").set("lowerbound", 0.001);
    model.result("pg2").feature("fun1").set("linecolor", "cyclereset");
    model.result("pg2").feature("fun1").set("legend", true);
    model.result("pg2").feature("fun1").set("legendmethod", "manual");
    model.result("pg2").feature("fun1").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.1", 0);
    model.result("pg2").feature().duplicate("fun2", "fun1");
    model.result("pg2").feature("fun2")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.3,n,Psi,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.3,n,Psi,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature("fun2").set("linecolor", "cycle");
    model.result("pg2").feature("fun2").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.3", 0);
    model.result("pg2").feature().duplicate("fun3", "fun2");
    model.result("pg2").feature("fun3")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.5,n,Psi,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.5,n,Psi,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature("fun3").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.5", 0);
    model.result("pg2").feature().duplicate("fun4", "fun3");
    model.result("pg2").feature("fun4")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.7,n,Psi,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.7,n,Psi,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature("fun4").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.7", 0);
    model.result("pg2").feature().duplicate("fun5", "fun4");
    model.result("pg2").feature("fun5")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.9,n,Psi,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.9,n,Psi,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature("fun5").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.9", 0);
    model.result("pg2").create("fun6", "Function");
    model.result("pg2").feature("fun6").set("linewidth", "preference");
    model.result("pg2").feature("fun6").set("data", "grid2");
    model.result("pg2").feature("fun6")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.1,n,Psi,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.1,n,Psi,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("fun6").set("xdataexpr", "Psi");
    model.result("pg2").feature("fun6").set("lowerbound", 0.001);
    model.result("pg2").feature("fun6").set("linestyle", "dashed");
    model.result("pg2").feature("fun6").set("linecolor", "cyclereset");
    model.result("pg2").feature().duplicate("fun7", "fun6");
    model.result("pg2").feature("fun7")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.3,n,Psi,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.3,n,Psi,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature("fun7").set("linecolor", "cycle");
    model.result("pg2").feature().duplicate("fun8", "fun7");
    model.result("pg2").feature("fun8")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.5,n,Psi,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.5,n,Psi,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature().duplicate("fun9", "fun8");
    model.result("pg2").feature("fun9")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.7,n,Psi,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.7,n,Psi,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").feature().duplicate("fun10", "fun9");
    model.result("pg2").feature("fun10")
         .set("expr", "sqrt((integrate(integrate((24/pi^3)*sum(pbar_summand(0.9,n,Psi,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate((24/pi^3)*sum(pbar_summand(0.9,n,Psi,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))^2)/L");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("table", "tbl5");
    model.result("pg2").feature("tblp1").set("xaxisdata", 2);
    model.result("pg2").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg2").feature("tblp1").set("linestyle", "none");
    model.result("pg2").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg2").feature("tblp1").set("linemarker", "cyclereset");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6570\u503c\uff0c\\epsilon=0.1", 0);
    model.result("pg2").feature().duplicate("tblp2", "tblp1");
    model.result("pg2").feature("tblp2").set("table", "tbl6");
    model.result("pg2").feature("tblp2").set("linecolor", "cycle");
    model.result("pg2").feature("tblp2").set("linemarker", "cycle");
    model.result("pg2").feature("tblp2").setIndex("legends", "\u6570\u503c\uff0c\\epsilon=0.3", 0);
    model.result("pg2").feature().duplicate("tblp3", "tblp2");
    model.result("pg2").feature("tblp3").set("table", "tbl7");
    model.result("pg2").feature("tblp3").setIndex("legends", "\u6570\u503c\uff0c\\epsilon=0.5", 0);
    model.result("pg2").feature().duplicate("tblp4", "tblp3");
    model.result("pg2").feature("tblp4").set("table", "tbl8");
    model.result("pg2").feature("tblp4").setIndex("legends", "\u6570\u503c\uff0c\\epsilon=0.7", 0);
    model.result("pg2").feature().duplicate("tblp5", "tblp4");
    model.result("pg2").feature("tblp5").set("table", "tbl9");
    model.result("pg2").feature("tblp5").setIndex("legends", "\u6570\u503c\uff0c\\epsilon=0.9", 0);
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("data", "dset1");
    model.result("pg2").feature("ann1")
         .set("text", "\\unicode{\u865a\u7ebf\uff1a}s=0\\\\\\unicode{\u5b9e\u7ebf\uff1a}s=0.25");
    model.result("pg2").feature("ann1").set("latexmarkup", true);
    model.result("pg2").feature("ann1").set("posxexpr", 0.002);
    model.result("pg2").feature("ann1").set("posyexpr", 0.02);
    model.result("pg2").feature("ann1").set("showpoint", false);
    model.result("pg2").feature("ann1").set("showframe", true);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u65e0\u91cf\u7eb2\u627f\u8f7d vs. ep\uff1as=0.5");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u4e0d\u540c \\Psi \u7684\u65e0\u91cf\u7eb2\u627f\u8f7d");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\\epsilon");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "W_nd");
    model.result("pg3").set("ylog", true);
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").create("fun1", "Function");
    model.result("pg3").feature("fun1").set("linewidth", "preference");
    model.result("pg3").feature("fun1").set("data", "grid1");
    model.result("pg3").feature("fun1")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,1,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,1,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg3").feature("fun1").set("xdataexpr", "ep");
    model.result("pg3").feature("fun1").set("linecolor", "cyclereset");
    model.result("pg3").feature("fun1").set("legend", true);
    model.result("pg3").feature("fun1").set("legendmethod", "manual");
    model.result("pg3").feature("fun1").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=1", 0);
    model.result("pg3").feature().duplicate("fun2", "fun1");
    model.result("pg3").feature("fun2")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,0.1,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,0.1,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg3").feature("fun2").set("linecolor", "cycle");
    model.result("pg3").feature("fun2").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.1", 0);
    model.result("pg3").feature().duplicate("fun3", "fun2");
    model.result("pg3").feature("fun3")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,0.01,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,0.01,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg3").feature("fun3").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.01", 0);
    model.result("pg3").feature().duplicate("fun4", "fun3");
    model.result("pg3").feature("fun4")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,0.001,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*ep*g_n(ep,n,0.001,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg3").feature("fun4").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.001", 0);
    model.result("pg3").feature().copy("fun5", "pg1/fun5");
    model.result("pg3").feature().copy("fun6", "pg1/fun6");
    model.result("pg3").feature().copy("fun7", "pg1/fun7");
    model.result("pg3").feature().copy("fun8", "pg1/fun8");
    model.result("pg3").feature().copy("tblp1", "pg1/tblp1");
    model.result("pg3").feature().copy("tblp2", "pg1/tblp2");
    model.result("pg3").feature().copy("tblp3", "pg1/tblp3");
    model.result("pg3").feature().copy("tblp4", "pg1/tblp4");
    model.result("pg3").feature().copy("ann1", "pg1/ann1");
    model.result("pg3").feature("tblp1").set("table", "tbl10");
    model.result("pg3").feature("tblp2").set("table", "tbl11");
    model.result("pg3").feature("tblp3").set("table", "tbl12");
    model.result("pg3").feature("tblp4").set("table", "tbl13");
    model.result("pg3").feature("ann1")
         .set("text", "\\unicode{\u865a\u7ebf\uff1a}s=0\\\\\\unicode{\u5b9e\u7ebf\uff1a}s=0.5");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u65e0\u91cf\u7eb2\u627f\u8f7d vs. Psi\uff1as=0.5");
    model.result("pg4").set("data", "none");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u4e0d\u540c \\epsilon \u7684\u65e0\u91cf\u7eb2\u627f\u8f7d");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\\Psi");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "W_nd");
    model.result("pg4").set("ylog", true);
    model.result("pg4").set("xlog", true);
    model.result("pg4").create("fun1", "Function");
    model.result("pg4").feature("fun1").set("linewidth", "preference");
    model.result("pg4").feature("fun1").set("data", "grid2");
    model.result("pg4").feature("fun1")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.1*g_n(0.1,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.1*g_n(0.1,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg4").feature("fun1").set("xdataexpr", "Psi");
    model.result("pg4").feature("fun1").set("lowerbound", 0.001);
    model.result("pg4").feature("fun1").set("linecolor", "cyclereset");
    model.result("pg4").feature("fun1").set("legend", true);
    model.result("pg4").feature("fun1").set("legendmethod", "manual");
    model.result("pg4").feature("fun1").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.1", 0);
    model.result("pg4").feature().duplicate("fun2", "fun1");
    model.result("pg4").feature("fun2")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.3*g_n(0.3,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.3*g_n(0.3,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg4").feature("fun2").set("linecolor", "cycle");
    model.result("pg4").feature("fun2").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.3", 0);
    model.result("pg4").feature().duplicate("fun3", "fun2");
    model.result("pg4").feature("fun3")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.5*g_n(0.5,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.5*g_n(0.5,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg4").feature("fun3").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.5", 0);
    model.result("pg4").feature().duplicate("fun4", "fun3");
    model.result("pg4").feature("fun4")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.7*g_n(0.7,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.7*g_n(0.7,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg4").feature("fun4").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.7", 0);
    model.result("pg4").feature().duplicate("fun5", "fun4");
    model.result("pg4").feature("fun5")
         .set("expr", "(C^2/(mu*U*L^3))*sqrt((integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.9*g_n(0.9,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*cos(theta)*R,theta,0,pi),z,-L/2,L/2))^2+(integrate(integrate(((mu*U*L^2)/(R*C^2))*(24/pi^3)*sum((-1)^(n+1)*0.9*g_n(0.9,n,Psi,0.5,theta)*cos(pi*beta_n(n)*z/L)/(2*n-1)^3,n,1,n_upper_lim)*sin(theta)*R,theta,0,pi),z,-L/2,L/2))^2)");
    model.result("pg4").feature("fun5").setIndex("legends", "\u89e3\u6790\uff0c\\epsilon=0.9", 0);
    model.result("pg4").feature().copy("fun6", "pg2/fun6");
    model.result("pg4").feature().copy("fun7", "pg2/fun7");
    model.result("pg4").feature().copy("fun8", "pg2/fun8");
    model.result("pg4").feature().copy("fun9", "pg2/fun9");
    model.result("pg4").feature().copy("fun10", "pg2/fun10");
    model.result("pg4").feature().copy("tblp1", "pg2/tblp1");
    model.result("pg4").feature().copy("tblp2", "pg2/tblp2");
    model.result("pg4").feature().copy("tblp3", "pg2/tblp3");
    model.result("pg4").feature().copy("tblp4", "pg2/tblp4");
    model.result("pg4").feature().copy("tblp5", "pg2/tblp5");
    model.result("pg4").feature().copy("ann1", "pg2/ann1");
    model.result("pg4").feature("tblp1").set("table", "tbl14");
    model.result("pg4").feature("tblp2").set("table", "tbl15");
    model.result("pg4").feature("tblp3").set("table", "tbl16");
    model.result("pg4").feature("tblp4").set("table", "tbl17");
    model.result("pg4").feature("tblp5").set("table", "tbl18");
    model.result("pg4").feature("ann1")
         .set("text", "\\unicode{\u865a\u7ebf\uff1a}s=0\\\\\\unicode{\u5b9e\u7ebf\uff1a}s=0.5");
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").label("\u59ff\u6001\u89d2 vs. Psi");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u4e0d\u540c \\Psi \u7684\u59ff\u6001\u89d2");
    model.result("pg5").set("zeroangle", "down");
    model.result("pg5").set("rotdir", "cw");
    model.result("pg5").label("\u59ff\u6001\u89d2 vs. ep\uff1as=0.25");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "grid1");
    model.result("pg5").feature("lngr1").set("expr", "ep");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").feature("lngr1").set("linecolor", "cyclereset");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=1", 0);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").feature("lngr2")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").feature("lngr2").set("linecolor", "cycle");
    model.result("pg5").feature("lngr2").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.1", 0);
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").feature("lngr3")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").feature("lngr3").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.01", 0);
    model.result("pg5").feature().duplicate("lngr4", "lngr3");
    model.result("pg5").feature("lngr4")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0.25,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0.25,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").feature("lngr4").setIndex("legends", "\u89e3\u6790\uff0c\\Psi=0.001", 0);
    model.result("pg5").create("lngr5", "LineGraph");
    model.result("pg5").feature("lngr5").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr5").set("linewidth", "preference");
    model.result("pg5").feature("lngr5").set("data", "grid1");
    model.result("pg5").feature("lngr5").set("expr", "ep");
    model.result("pg5").feature("lngr5").set("xdata", "expr");
    model.result("pg5").feature("lngr5")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,1,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").feature("lngr5").set("linestyle", "dashed");
    model.result("pg5").feature("lngr5").set("linecolor", "cyclereset");
    model.result("pg5").feature().duplicate("lngr6", "lngr5");
    model.result("pg5").feature("lngr6").set("linecolor", "cycle");
    model.result("pg5").feature("lngr6")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.1,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").feature().duplicate("lngr7", "lngr6");
    model.result("pg5").feature("lngr7")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.01,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").feature().duplicate("lngr8", "lngr7");
    model.result("pg5").feature("lngr8")
         .set("xdataexpr", "atan(-(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0,theta,z),n,1,n_upper_lim)*sin(theta),theta,0,pi),z,-L/2,L/2))/(integrate(integrate((24/pi^3)*sum(pbar_summand(ep,n,0.001,0,theta,z),n,1,n_upper_lim)*cos(theta),theta,0,pi),z,-L/2,L/2)))");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("xaxisdata", 5);
    model.result("pg5").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg5").feature("tblp1").set("linestyle", "none");
    model.result("pg5").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg5").feature("tblp1").set("linemarker", "cyclereset");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("legendmethod", "manual");
    model.result("pg5").feature("tblp1").setIndex("legends", "\u6570\u503c\uff0c\\Psi=1", 0);
    model.result("pg5").feature().duplicate("tblp2", "tblp1");
    model.result("pg5").feature("tblp2").set("table", "tbl2");
    model.result("pg5").feature("tblp2").set("linecolor", "cycle");
    model.result("pg5").feature("tblp2").set("linemarker", "cycle");
    model.result("pg5").feature("tblp2").setIndex("legends", "\u6570\u503c\uff0c\\Psi=0.1", 0);
    model.result("pg5").feature().duplicate("tblp3", "tblp2");
    model.result("pg5").feature("tblp3").set("table", "tbl3");
    model.result("pg5").feature("tblp3").setIndex("legends", "\u6570\u503c\uff0c\\Psi=0.01", 0);
    model.result("pg5").feature().duplicate("tblp4", "tblp3");
    model.result("pg5").feature("tblp4").set("table", "tbl4");
    model.result("pg5").feature("tblp4").setIndex("legends", "\u6570\u503c\uff0c\\Psi=0.001", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6")
         .label("\u8fbe\u897f\u901f\u5ea6\u6d41\u7ebf\u3001\u819c\u7b49\u538b\u7ebf\u548c\u819c\u539a");
    model.result("pg6").set("legendpos", "rightdouble");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").setIndex("expr", "dl.u", 0);
    model.result("pg6").feature("str1").setIndex("expr", "dl.v", 1);
    model.result("pg6").feature("str1").set("expr", new String[]{"dl.u", "dl.v", "dl.w"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("color", "black");
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("number", 50);
    model.result("pg6").feature("con1").set("legendtype", "filled");
    model.result("pg6").feature("con1").create("sel1", "Selection");
    model.result("pg6").feature("con1").feature("sel1").selection().set(8, 9, 14, 16);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "tff.h");
    model.result("pg6").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().set(8, 9, 14, 16);
    model.result("pg6").feature("surf1").create("tran1", "Transparency");
    model.result("pg6").feature("surf1").feature("tran1").set("transparency", 0.1);
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("expr", "1");
    model.result("pg6").feature("surf2").set("titletype", "none");
    model.result("pg6").feature("surf2").set("coloring", "uniform");
    model.result("pg6").feature("surf2").set("color", "gray");
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().set(2, 3, 4, 5, 6, 7, 11, 12, 13, 17, 18, 19);
    model.result("pg6").feature("surf2").create("tran1", "Transparency");
    model.result("pg6").set("view", "new");
    model.result("pg6").run();

    model.title("\u81ea\u6da6\u6ed1\u8f74\u9888\u8f74\u627f");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5982\u4f55\u6a21\u62df\u6db2\u4f53\u6da6\u6ed1\u5242\u5728\u8f74\u9888\u8f74\u627f\u4e2d\u7684\u81ea\u6da6\u6ed1\u73b0\u8c61\uff0c\u5176\u4e2d\u5c06\u591a\u5b54\u886c\u5957\u538b\u88c5\u5230\u8f74\u627f\u4e0a\u5e76\u5c06\u5176\u6d78\u6da6\u4e8e\u6da6\u6ed1\u6cb9\u4e2d\u3002\u8fd9\u4e2a\u591a\u5b54\u886c\u5957\u5145\u5f53\u50a8\u5c42\uff0c\u7528\u4e8e\u5728\u886c\u5957\u4e0e\u8f74\u9888\u4e4b\u95f4\u7684\u95f4\u9699\u4e2d\u6839\u636e\u4e0d\u540c\u7684\u538b\u529b\u503c\u533a\u57df\u91cd\u65b0\u5206\u914d\u6da6\u6ed1\u6cb9\u8584\u819c\u3002");

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
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();

    model.label("self_lubricating_bearing.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
