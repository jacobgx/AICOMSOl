/*
 * electrodialysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:53 by COMSOL 6.3.0.290. */
public class electrodialysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cNa");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cNa", "cCl"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Vtot", "1.5[V]", "\u7535\u6e17\u6790\u5355\u5143\u7684\u603b\u7535\u4f4d\u964d");
    model.param().set("DNa", "2.5e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cNa");
    model.param().set("DCl", "2e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cCl");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("cCl_0", "0.5[mol/dm^3]", "\u5165\u53e3\u6d53\u5ea6\uff0cCl");
    model.param().set("cMem", "1[mol/dm^3]", "\u819c\u7535\u8377\u6d53\u5ea6");
    model.param().set("v_avg", "0.005[m/s]", "\u901a\u9053\u5e73\u5747\u6d41\u901f");
    model.param().set("L", "0.01[m]", "\u5355\u5143\u957f\u5ea6");
    model.param().set("W_ch", "1[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("W_m", "0.25[mm]", "\u819c\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"2*(W_ch+W_m)", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "L", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-(W_ch+W_m)", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"(W_ch+2*W_m)", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "L", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-(W_ch+2*W_m)/2", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"W_ch", "L"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-W_ch/2", "0"});
    model.component("comp1").geom("geom1").run("r3");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u819c\uff08\u9633\u79bb\u5b50\u9009\u62e9\u6027\uff09");
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u819c\uff08\u9634\u79bb\u5b50\u9009\u62e9\u6027\uff09");
    model.component("comp1").selection("sel2").set(4);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u901a\u9053");
    model.component("comp1").selection("sel3").set(1, 3, 5);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5165\u53e3");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set(2, 8, 14);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u51fa\u53e3");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(3, 9, 15);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "vel");
    model.component("comp1").func("an1").set("expr", "3*v_avg*(1-((t)/(W_ch/2))^2)");
    model.component("comp1").func("an1").set("args", "t");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").set("fununit", "m/s");
    model.component("comp1").func("an1").setIndex("plotargs", -1, 0, 1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().set(3);
    model.component("comp1").variable("var1").set("vel", "vel(x)");
    model.component("comp1").variable("var1")
         .descr("vel", "\u901a\u9053\u901f\u5ea6\uff08\u4e2d\u95f4\u901a\u9053\uff09");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().set(1);
    model.component("comp1").variable("var2")
         .set("vel", "vel((x+W_ch+W_m))", "\u901a\u9053\u901f\u5ea6\uff08\u4e2d\u95f4\u901a\u9053\uff09");
    model.component("comp1").variable("var2").descr("vel", "\u901a\u9053\u901f\u5ea6\uff08\u5de6\u901a\u9053\uff09");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().set(5);
    model.component("comp1").variable("var3")
         .set("vel", "vel((x-W_ch-W_m))", "\u901a\u9053\u901f\u5ea6\uff08\u4e2d\u95f4\u901a\u9053\uff09");
    model.component("comp1").variable("var3").descr("vel", "\u901a\u9053\u901f\u5ea6\uff08\u53f3\u901a\u9053\uff09");

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tcd").feature("ice1").set("u", new String[]{"0", "vel", "0"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cNa", new String[]{"DNa", "0", "0", "0", "DNa", "0", "0", "0", "DNa"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cCl", new String[]{"DCl", "0", "0", "0", "DCl", "0", "0", "0", "DCl"});
    model.component("comp1").physics("tcd").create("iem1", "IonExchangeMembrane", 2);
    model.component("comp1").physics("tcd").feature("iem1")
         .label("\u79bb\u5b50\u4ea4\u6362\u819c\uff08\u9633\u79bb\u5b50\u9009\u62e9\u6027\uff09");
    model.component("comp1").physics("tcd").feature("iem1").selection().named("sel1");
    model.component("comp1").physics("tcd").feature("iem1").set("rhofix", "-cMem*F_const");
    model.component("comp1").physics("tcd").feature("iem1")
         .set("D_cNa", new String[]{"DNa", "0", "0", "0", "DNa", "0", "0", "0", "DNa"});
    model.component("comp1").physics("tcd").feature("iem1")
         .set("D_cCl", new String[]{"DCl", "0", "0", "0", "DCl", "0", "0", "0", "DCl"});
    model.component("comp1").physics("tcd").feature("iem1").set("epsl", 0.15);
    model.component("comp1").physics("tcd").feature().duplicate("iem2", "iem1");
    model.component("comp1").physics("tcd").feature("iem2")
         .label("\u79bb\u5b50\u4ea4\u6362\u819c\uff08\u9634\u79bb\u5b50\u9009\u62e9\u6027\uff09");
    model.component("comp1").physics("tcd").feature("iem2").selection().named("sel2");
    model.component("comp1").physics("tcd").feature("iem2").set("rhofix", "cMem*F_const");
    model.component("comp1").physics("tcd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("tcd").feature("pc1").selection().set(1, 16);
    model.component("comp1").physics("tcd").feature("pc1").set("deltaphil", "-Vtot");
    model.component("comp1").physics("tcd").feature("pc1").set("ApplyForElectrodePhase", false);
    model.component("comp1").physics("tcd").create("eip1", "ElectrolytePotentialPoint", 0);
    model.component("comp1").physics("tcd").feature("eip1").selection().set(1);
    model.component("comp1").physics("tcd").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcd").feature("in1").selection().named("sel4");
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cCl_0", 1);
    model.component("comp1").physics("tcd").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tcd").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcd").feature("out1").selection().named("sel5");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cCl_0", 1);
    model.component("comp1").physics("tcd").feature("init1").set("initphil", "Vtot*x/L");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 100);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(8, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(3, 14);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("symmetric", false);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis4", "dis3");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(2, 15);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("reverse", false);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis5", "dis4");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(6, 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Vtot", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "Vtot", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "cMem", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 500 1000", 0);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-5");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").label("\u6d53\u5ea6, Na (tcd)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("prefixintitle", "");
    model.result("pg3").set("expressionintitle", false);
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cNa"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tcd.tflux_cNax", "tcd.tflux_cNay"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").label("\u6d53\u5ea6, Cl (tcd)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cCl"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").set("typeintitle", true);
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"tcd.tflux_cClx", "tcd.tflux_cCly"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").selection().geom("geom1", 2);
    model.result("pg3").selection().named("sel3");
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("posmethod", "selection");
    model.result("pg3").feature("str1").set("selnumber", 5);
    model.result("pg3").feature("str1").selection().named("sel4");
    model.result("pg3").run();
    model.result("pg3").set("solutionintitle", false);
    model.result("pg3").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "-W_ch-W_m", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "W_ch+W_m", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L/2", 1, 1);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u4f4d");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u4f4d\u7f6e");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6d53\u5ea6");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u4f4d\u7f6e");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "cNa");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "Na<sup>+</sup>", 0);
    model.result("pg6").run();
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").set("expr", "cCl");
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "Cl<sup>-</sup>", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u901a\u91cf\uff0cNa");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "tcd.tfluxx_cNa");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "\u603b\u901a\u91cf", 0);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("expr", "tcd.mflux_cNax");
    model.result("pg7").feature("lngr2").setIndex("legends", "\u8fc1\u79fb\u901a\u91cf", 0);
    model.result("pg7").feature().duplicate("lngr3", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").set("expr", "tcd.dflux_cNax");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").setIndex("legends", "\u6269\u6563\u901a\u91cf", 0);
    model.result("pg7").run();
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u4f4d\u7f6e");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u901a\u91cf");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").label("\u901a\u91cf\uff0cCl");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "tcd.tfluxx_cCl");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "tcd.mflux_cClx");
    model.result("pg8").run();
    model.result("pg8").feature("lngr3").set("expr", "tcd.dflux_cClx");
    model.result("pg8").run();
    model.result("pg8").set("title", "Cl<sup>-</sup> \u901a\u91cf\uff0cx \u5206\u91cf mol/(m<sup>2</sup>*s))");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().set(10);
    model.result("pg9").feature("lngr1").set("expr", "tcd.nIl");
    model.result("pg9").feature("lngr1").set("descr", "\u6cd5\u5411\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "y");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").run();

    model.title("\u7535\u6e17\u6790\u69fd\u4e2d\u8131\u76d0");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4ece\u6c34\u6eb6\u6db2\uff08\u7a00\uff09\u4e2d\u8131\u9664\u6c2f\u5316\u94a0\uff0c\u5e76\u5c06\u5176\u8f6c\u79fb\u5230\u53e6\u4e00\u6d53\u6eb6\u6db2\u4e2d\u3002\u7535\u6e17\u6790\u7684\u5178\u578b\u5e94\u7528\u5305\u62ec\u5de5\u827a\u7269\u6d41\u8131\u76d0\u3001\u98df\u54c1\u548c\u679c\u6c41\u4ea7\u54c1\u3001\u751f\u7269\u533b\u5b66\u5de5\u7a0b\u7b49\u9886\u57df\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("electrodialysis.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
