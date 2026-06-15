/*
 * transformation_diagram_computation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:07 by COMSOL 6.3.0.290. */
public class transformation_diagram_computation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Metal_Processing_Module\\Transformation_Diagrams");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("audc", "GlobalAusteniteDecomposition");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/audc", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("highT", "900[degC]", "\u6700\u9ad8\u8f6c\u53d8\u6e29\u5ea6");
    model.param().set("lowT", "100[degC]", "\u6700\u4f4e\u8f6c\u53d8\u6e29\u5ea6");
    model.param().set("startFraction", "0.01", "\u8868\u793a\u53d8\u6362\u5f00\u59cb\u7684\u76f8\u5206\u6570");
    model.param().set("rateT", "1[K/s]", "\u51b7\u5374\u901f\u7387\u53c2\u6570");
    model.param().set("T0", "highT", "\u51b7\u5374\u6e29\u5ea6\u53c2\u6570");
    model.param().set("nRates", "10", "CCT \u7684\u6bcf\u5341\u500d\u9891\u7387\u51b7\u5374\u901f\u7387\u6570");
    model.param().set("highRate", "100[K/s]", "CCT \u7684\u6700\u9ad8\u51b7\u5374\u901f\u7387");
    model.param().set("lowRate", "0.01[K/s]", "CCT \u7684\u6700\u4f4e\u51b7\u5374\u901f\u7387");
    model.param().set("nTemps", "50", "TTT \u7684\u6e29\u5ea6\u6570");
    model.param().set("maxTime", "2500[s]", "\u6700\u5927\u8f6c\u53d8\u65f6\u95f4");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("CCT");
    model.component("comp1").variable("var1").set("T", "T0-rateT*t");
    model.component("comp1").variable("var1").descr("T", "CCT \u6e29\u5ea6");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("TTT");
    model.component("comp1").variable("var2").set("T", "T0", "CCT \u6e29\u5ea6");
    model.component("comp1").variable("var2").descr("T", "TTT \u6e29\u5ea6");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "K_Austenite_to_Ferrite");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"550", "0"}, 
         {"620", "0.002"}, 
         {"700", "0.001"}, 
         {"750", "0"}, 
         {"1000", "0"}, 
         {"", ""}});
    model.component("comp1").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").func("int1").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int1").setIndex("fununit", "1/s", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("funcname", "L_Austenite_to_Ferrite");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"0", "0"}, {"600", "0"}, {"620", "0.0002"}, {"800", "0.002"}, {"1000", "0.002"}});
    model.component("comp1").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").func("int2").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int2").setIndex("fununit", "1/s", 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").set("funcname", "K_Austenite_to_Bainite");
    model.component("comp1").func("int3")
         .set("table", new String[][]{{"0", "0"}, 
         {"380", "0"}, 
         {"400", "0.0005"}, 
         {"490", "0.005"}, 
         {"580", "0.00005"}, 
         {"600", "0"}});
    model.component("comp1").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").func("int3").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int3").setIndex("fununit", "1/s", 0);
    model.component("comp1").func().create("int4", "Interpolation");
    model.component("comp1").func("int4").set("funcname", "L_Austenite_to_Bainite");
    model.component("comp1").func("int4")
         .set("table", new String[][]{{"0", "0"}, {"400", "0.0"}, {"500", "0.0002"}, {"580", "0.002"}, {"600", "0.002"}});
    model.component("comp1").func("int4").set("interp", "piecewisecubic");
    model.component("comp1").func("int4").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int4").setIndex("fununit", "1/s", 0);

    model.component("comp1").physics("audc").prop("Temperature").set("T", "T");
    model.component("comp1").physics("audc").feature("phase2").set("computetimes", true);
    model.component("comp1").physics("audc").feature("phase4").set("computetimes", true);
    model.component("comp1").physics("audc").feature("ptran1").set("K", "K_Austenite_to_Ferrite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran1").set("L", "L_Austenite_to_Ferrite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran3").set("K", "K_Austenite_to_Bainite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran3").set("L", "L_Austenite_to_Bainite(audc.T)");
    model.component("comp1").physics("audc").feature("ptran2").active(false);
    model.component("comp1").physics("audc").feature("phase3").active(false);
    model.component("comp1").physics("audc").feature("ptran4").active(false);
    model.component("comp1").physics("audc").feature("phase5").active(false);

    model.study("std1").label("CCT");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "audc.beta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/K", 0);
    model.study("std1").feature("param").setIndex("pname", "audc.beta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "1/K", 0);
    model.study("std1").feature("param").setIndex("pname", "rateT", 0);
    model.study("std1").feature("param")
         .setIndex("plistarr", "10^{range(log10(highRate),-(1/nRates),log10(lowRate))}", 0);
    model.study("std1").feature("time").set("tlist", "range(0,((T0-lowT)/rateT-0)/49,(T0-lowT)/rateT)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledvariables", new String[]{"var2"});
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"audc.phase1.xi", "audc.phase2.xi", "audc.phase4.xi"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5206\u6570", "\u76f8\u5206\u6570", "\u76f8\u5206\u6570"});
    model.result("pg1").label("\u76f8\u7ec4\u6210 (audc)");
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u5965\u6c0f\u4f53", "\u94c1\u7d20\u4f53", "\u8d1d\u6c0f\u4f53"});
    model.result("pg1").feature("glob1").set("title", "\u76f8\u7ec4\u6210");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob2").setIndex("expr", "audc.T", 0);
    model.result("pg1").feature("glob2").set("linestyle", "dotted");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").label("\u76f8\u53d8\u56fe (audc)");
    model.result("pg2").set("innerinput", "last");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").set("xlabel", "\u65f6\u95f4");
    model.result("pg2").set("ylabel", "\u6e29\u5ea6");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", new String[]{"audc.phase2.temperature_1"});
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "audc.phase2.time_1");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u94c1\u7d20\u4f53 0.01", 0);
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("expr", new String[]{"audc.phase2.temperature_2"});
    model.result("pg2").feature("glob2").set("titletype", "none");
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "audc.phase2.time_2");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "\u94c1\u7d20\u4f53 0.99", 0);
    model.result("pg2").feature("glob2").set("linemarker", "cycle");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "all");
    model.result("pg2").create("glob3", "Global");
    model.result("pg2").feature("glob3").set("expr", new String[]{"audc.phase4.temperature_1"});
    model.result("pg2").feature("glob3").set("titletype", "none");
    model.result("pg2").feature("glob3").set("xdata", "expr");
    model.result("pg2").feature("glob3").set("xdataexpr", "audc.phase4.time_1");
    model.result("pg2").feature("glob3").set("legendmethod", "manual");
    model.result("pg2").feature("glob3").setIndex("legends", "\u8d1d\u6c0f\u4f53 0.01", 0);
    model.result("pg2").feature("glob3").set("linemarker", "cycle");
    model.result("pg2").feature("glob3").set("markerpos", "datapoints");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "all");
    model.result("pg2").create("glob4", "Global");
    model.result("pg2").feature("glob4").set("expr", new String[]{"audc.phase4.temperature_2"});
    model.result("pg2").feature("glob4").set("titletype", "none");
    model.result("pg2").feature("glob4").set("xdata", "expr");
    model.result("pg2").feature("glob4").set("xdataexpr", "audc.phase4.time_2");
    model.result("pg2").feature("glob4").set("legendmethod", "manual");
    model.result("pg2").feature("glob4").setIndex("legends", "\u8d1d\u6c0f\u4f53 0.99", 0);
    model.result("pg2").feature("glob4").set("linemarker", "cycle");
    model.result("pg2").feature("glob4").set("markerpos", "datapoints");
    model.result("pg2").feature("glob4").set("xdatasolnumtype", "all");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/audc", true);
    model.study("std2").label("TTT");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").set("sweeptype", "filled");
    model.study("std2").feature("param").setIndex("pname", "audc.beta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "1/K", 0);
    model.study("std2").feature("param").setIndex("pname", "audc.beta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "1/K", 0);
    model.study("std2").feature("param").setIndex("pname", "T0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(highT,(lowT-(highT))/(nTemps-1),lowT)", 0);
    model.study("std2").feature("time").set("tlist", "range(0,maxTime/99,maxTime)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledvariables", new String[]{"var1"});
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol45");
    model.sol("sol45").study("std2");
    model.sol("sol45").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol45");
    model.batch("p2").run("compute");

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"audc.phase1.xi", "audc.phase2.xi", "audc.phase4.xi"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5206\u6570", "\u76f8\u5206\u6570", "\u76f8\u5206\u6570"});
    model.result("pg3").label("\u76f8\u7ec4\u6210 (audc) 1");
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u5965\u6c0f\u4f53", "\u94c1\u7d20\u4f53", "\u8d1d\u6c0f\u4f53"});
    model.result("pg3").feature("glob1").set("title", "\u76f8\u7ec4\u6210");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").set("legendlayout", "outside");
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg3").feature("glob2").setIndex("expr", "audc.T", 0);
    model.result("pg3").feature("glob2").set("linestyle", "dotted");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").label("\u76f8\u53d8\u56fe (audc) 1");
    model.result("pg4").set("innerinput", "last");
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").set("xlabel", "\u65f6\u95f4");
    model.result("pg4").set("ylabel", "\u6e29\u5ea6");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("expr", new String[]{"audc.phase2.temperature_1"});
    model.result("pg4").feature("glob1").set("titletype", "none");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "audc.phase2.time_1");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u94c1\u7d20\u4f53 0.01", 0);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("expr", new String[]{"audc.phase2.temperature_2"});
    model.result("pg4").feature("glob2").set("titletype", "none");
    model.result("pg4").feature("glob2").set("xdata", "expr");
    model.result("pg4").feature("glob2").set("xdataexpr", "audc.phase2.time_2");
    model.result("pg4").feature("glob2").set("legendmethod", "manual");
    model.result("pg4").feature("glob2").setIndex("legends", "\u94c1\u7d20\u4f53 0.99", 0);
    model.result("pg4").feature("glob2").set("linemarker", "cycle");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("xdatasolnumtype", "all");
    model.result("pg4").create("glob3", "Global");
    model.result("pg4").feature("glob3").set("expr", new String[]{"audc.phase4.temperature_1"});
    model.result("pg4").feature("glob3").set("titletype", "none");
    model.result("pg4").feature("glob3").set("xdata", "expr");
    model.result("pg4").feature("glob3").set("xdataexpr", "audc.phase4.time_1");
    model.result("pg4").feature("glob3").set("legendmethod", "manual");
    model.result("pg4").feature("glob3").setIndex("legends", "\u8d1d\u6c0f\u4f53 0.01", 0);
    model.result("pg4").feature("glob3").set("linemarker", "cycle");
    model.result("pg4").feature("glob3").set("markerpos", "datapoints");
    model.result("pg4").feature("glob3").set("xdatasolnumtype", "all");
    model.result("pg4").create("glob4", "Global");
    model.result("pg4").feature("glob4").set("expr", new String[]{"audc.phase4.temperature_2"});
    model.result("pg4").feature("glob4").set("titletype", "none");
    model.result("pg4").feature("glob4").set("xdata", "expr");
    model.result("pg4").feature("glob4").set("xdataexpr", "audc.phase4.time_2");
    model.result("pg4").feature("glob4").set("legendmethod", "manual");
    model.result("pg4").feature("glob4").setIndex("legends", "\u8d1d\u6c0f\u4f53 0.99", 0);
    model.result("pg4").feature("glob4").set("linemarker", "cycle");
    model.result("pg4").feature("glob4").set("markerpos", "datapoints");
    model.result("pg4").feature("glob4").set("xdatasolnumtype", "all");
    model.result("pg3").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result("pg2").create("glob5", "Global");
    model.result("pg2").feature("glob5").set("markerpos", "datapoints");
    model.result("pg2").feature("glob5").set("linewidth", "preference");
    model.result("pg2").feature("glob5").set("data", "dset2");
    model.result("pg2").feature("glob5").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").feature("glob5").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg2").feature("glob5").setIndex("expr", "audc.T", 0);
    model.result("pg2").feature("glob5").set("titletype", "none");
    model.result("pg2").feature().duplicate("glob6", "glob5");
    model.result("pg2").run();
    model.result("pg2").feature("glob6").setIndex("looplevel", new int[]{11}, 1);
    model.result("pg2").feature().duplicate("glob7", "glob6");
    model.result("pg2").run();
    model.result("pg2").feature("glob7").setIndex("looplevel", new int[]{21}, 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u76f8\u53d8\u56fe\u8ba1\u7b97");

    model
         .description("\u5728\u94a2\u7684\u6dec\u706b\u8fc7\u7a0b\u4e2d\uff0c\u5965\u6c0f\u4f53\u5206\u89e3\u6210\u94c1\u7d20\u4f53\u3001\u73e0\u5149\u4f53\u3001\u8d1d\u6c0f\u4f53\u548c\u9a6c\u6c0f\u4f53\u7b49\u76f8\u3002\u63cf\u8ff0\u7279\u79cd\u94a2\u5408\u91d1\u76f8\u53d8\u7279\u6027\u7684\u4e00\u79cd\u5e38\u89c1\u65b9\u6cd5\u662f\u4f7f\u7528\u76f8\u53d8\u56fe\uff0c\u5176\u4e2d\u4e24\u79cd\u6700\u5e38\u7528\u7684\u56fe\u8868\u7c7b\u578b\u662f CCT\uff08\u8fde\u7eed\u51b7\u5374\u8f6c\u53d8\uff09\u548c TTT\uff08\u65f6\u95f4\u6e29\u5ea6\u8f6c\u53d8\uff09\u56fe\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u57fa\u4e8e\u6e29\u5ea6\u76f8\u5173\u7684\u76f8\u53d8\u6570\u636e\u8ba1\u7b97 CCT \u548c TTT \u56fe\u3002");

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
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();
    model.sol("sol67").clearSolutionData();
    model.sol("sol68").clearSolutionData();
    model.sol("sol69").clearSolutionData();
    model.sol("sol70").clearSolutionData();
    model.sol("sol71").clearSolutionData();
    model.sol("sol72").clearSolutionData();
    model.sol("sol73").clearSolutionData();
    model.sol("sol74").clearSolutionData();
    model.sol("sol75").clearSolutionData();
    model.sol("sol76").clearSolutionData();
    model.sol("sol77").clearSolutionData();
    model.sol("sol78").clearSolutionData();
    model.sol("sol79").clearSolutionData();
    model.sol("sol80").clearSolutionData();
    model.sol("sol81").clearSolutionData();
    model.sol("sol82").clearSolutionData();
    model.sol("sol83").clearSolutionData();
    model.sol("sol84").clearSolutionData();
    model.sol("sol85").clearSolutionData();
    model.sol("sol86").clearSolutionData();
    model.sol("sol87").clearSolutionData();
    model.sol("sol88").clearSolutionData();
    model.sol("sol89").clearSolutionData();
    model.sol("sol90").clearSolutionData();
    model.sol("sol91").clearSolutionData();
    model.sol("sol92").clearSolutionData();
    model.sol("sol93").clearSolutionData();
    model.sol("sol94").clearSolutionData();
    model.sol("sol95").clearSolutionData();

    model.label("transformation_diagram_computation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
