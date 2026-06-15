/*
 * calibration_against_ttt_data.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:06 by COMSOL 6.3.0.290. */
public class calibration_against_ttt_data {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Metal_Processing_Module\\Transformation_Diagrams");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("metp", "GlobalMetalPhaseTransformation");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/metp", true);

    model.param().set("xieq", "1");
    model.param().descr("xieq", "\u5e73\u8861\u76f8\u5206\u6570");
    model.param().set("tau", "1[s]");
    model.param().descr("tau", "\u65f6\u95f4\u5e38\u6570");
    model.param().set("n", "3");
    model.param().descr("n", "Avrami \u6307\u6570");
    model.param().set("Tsw", "1[K]");
    model.param().descr("Tsw", "\u6e29\u5ea6\u626b\u63cf\u53c2\u6570");
    model.param().set("T", "1[K]");
    model.param().descr("T", "\u6e29\u5ea6");

    model.component("comp1").physics("metp").feature("phase2").set("computetimes", true);
    model.component("comp1").physics("metp").feature("phase2").setIndex("xitarget", 0, 2, 0);
    model.component("comp1").physics("metp").feature("phase2").setIndex("xitarget", 0, 2, 0);
    model.component("comp1").physics("metp").feature("phase2").setIndex("xitarget", 0.5, 1, 0);
    model.component("comp1").physics("metp").feature("phase2").setIndex("xitarget", 0.9, 2, 0);
    model.component("comp1").physics("metp").feature("ptran1").set("ptModel", "JMAK");
    model.component("comp1").physics("metp").feature("ptran1").set("xieqjmak", "xieq");
    model.component("comp1").physics("metp").feature("ptran1").set("taujmak", "tau");
    model.component("comp1").physics("metp").feature("ptran1").set("njmak", "n");

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").set("filename", "calibration_against_ttt_data_ttt001.txt");
    model.component("comp1").common("lso1").importData();
    model.component("comp1").common("lso1").setEntry("columnType", "col1", "param");
    model.component("comp1").common("lso1").setEntry("parameterName", "col1", "T");
    model.component("comp1").common("lso1").setEntry("parameterUnit", "col1", "K");
    model.component("comp1").common("lso1").setEntry("columnType", "col2", "time");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col3", "metp.phase2.xi");
    model.component("comp1").common("lso1").setEntry("variableName", "col3", "col3a");
    model.component("comp1").common("lso1").setEntry("scaleMethod", "col3", "manual");
    model.component("comp1").common("lso1").setEntry("scaleValue", "col3", "0.01");
    model.component("comp1").common().duplicate("lso2", "lso1");
    model.component("comp1").common("lso2").discardData();
    model.component("comp1").common("lso2").set("filename", "calibration_against_ttt_data_ttt050.txt");
    model.component("comp1").common("lso2").importData();
    model.component("comp1").common("lso2").setEntry("variableName", "col3", "col3b");
    model.component("comp1").common("lso2").setEntry("scaleValue", "col3", "0.5");
    model.component("comp1").common().duplicate("lso3", "lso2");
    model.component("comp1").common("lso3").discardData();
    model.component("comp1").common("lso3").set("filename", "calibration_against_ttt_data_ttt090.txt");
    model.component("comp1").common("lso3").importData();
    model.component("comp1").common("lso3").setEntry("variableName", "col3", "col3c");
    model.component("comp1").common("lso3").setEntry("scaleValue", "col3", "0.9");

    model.study("std1").feature("time").set("tlist", "10^{range(log10(0.1),1/10,log10(100000))}");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").setIndex("pname", "xieq", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "", 0);
    model.study("std1").feature("time").setIndex("pname", "xieq", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "", 0);
    model.study("std1").feature("time").setIndex("pname", "T", 0);
    model.study("std1").feature("time").setIndex("plistarr", "Tsw", 0);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "xieq", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "xieq", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "Tsw", 0);
    model.study("std1").feature("param").setIndex("plistarr", "853 893 933 973 1013 1053", 0);
    model.study("std1").create("lsqo", "LSQOptimization");
    model.study("std1").feature("lsqo").set("source", "none");
    model.study("std1").feature("lsqo").setIndex("pname", "xieq", 0);
    model.study("std1").feature("lsqo").setIndex("initval", 1, 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "xieq", 0);
    model.study("std1").feature("lsqo").setIndex("initval", 1, 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "tau", 0);
    model.study("std1").feature("lsqo").setIndex("initval", "2000[s]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1000, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "700[s]", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "28000[s]", 0);
    model.study("std1").feature("lsqo").set("optsolver", "bobyqa");
    model.study("std1").feature("lsqo").set("lsqdatamethod", "manual");
    model.study("std1").feature("lsqo").set("opttol", "0.0001");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p2").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"metp.phase1.xi", "metp.phase2.xi"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u76f8\u5206\u6570", "\u76f8\u5206\u6570"});
    model.result("pg1").label("\u76f8\u7ec4\u6210 (metp)");
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u91d1\u76f8 1", "\u91d1\u76f8 2"});
    model.result("pg1").feature("glob1").set("title", "\u76f8\u7ec4\u6210");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob2").setIndex("expr", "metp.T", 0);
    model.result("pg1").feature("glob2").set("linestyle", "dotted");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").label("\u76f8\u53d8\u56fe (metp)");
    model.result("pg2").set("innerinput", "last");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").set("xlabel", "\u65f6\u95f4");
    model.result("pg2").set("ylabel", "\u6e29\u5ea6");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", new String[]{"metp.phase2.temperature_1"});
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "metp.phase2.time_1");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u91d1\u76f8 2 0.01", 0);
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("expr", new String[]{"metp.phase2.temperature_2"});
    model.result("pg2").feature("glob2").set("titletype", "none");
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "metp.phase2.time_2");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "\u91d1\u76f8 2 0.5", 0);
    model.result("pg2").feature("glob2").set("linemarker", "cycle");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "all");
    model.result("pg2").create("glob3", "Global");
    model.result("pg2").feature("glob3").set("expr", new String[]{"metp.phase2.temperature_3"});
    model.result("pg2").feature("glob3").set("titletype", "none");
    model.result("pg2").feature("glob3").set("xdata", "expr");
    model.result("pg2").feature("glob3").set("xdataexpr", "metp.phase2.time_3");
    model.result("pg2").feature("glob3").set("legendmethod", "manual");
    model.result("pg2").feature("glob3").setIndex("legends", "\u91d1\u76f8 2 0.9", 0);
    model.result("pg2").feature("glob3").set("linemarker", "cycle");
    model.result("pg2").feature("glob3").set("markerpos", "datapoints");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u53c2\u6570\u4f30\u8ba1");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u6e29\u5ea6");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").label("\u5217 3 (\u6a21\u578b)");
    model.result("pg3").feature("glob1").set("expr", new String[]{"lso1.col3a.model"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u5217 3 (\u6a21\u578b)"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "T");
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").label("\u5217 3 (\u6570\u636e)");
    model.result("pg3").feature("glob2").set("expr", new String[]{"lso1.col3a.data"});
    model.result("pg3").feature("glob2").set("descr", new String[]{"\u5217 3 (\u6570\u636e)"});
    model.result("pg3").feature("glob2").set("xdata", "expr");
    model.result("pg3").feature("glob2").set("xdataexpr", "T");
    model.result("pg3").feature("glob2").set("linestyle", "none");
    model.result("pg3").feature("glob2").set("linemarker", "point");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linecolor", "cyclereset");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u53c2\u6570\u4f30\u8ba1 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6e29\u5ea6");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").label("\u5217 3 (\u6a21\u578b)");
    model.result("pg4").feature("glob1").set("expr", new String[]{"lso2.col3b.model"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u5217 3 (\u6a21\u578b)"});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "T");
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").label("\u5217 3 (\u6570\u636e)");
    model.result("pg4").feature("glob2").set("expr", new String[]{"lso2.col3b.data"});
    model.result("pg4").feature("glob2").set("descr", new String[]{"\u5217 3 (\u6570\u636e)"});
    model.result("pg4").feature("glob2").set("xdata", "expr");
    model.result("pg4").feature("glob2").set("xdataexpr", "T");
    model.result("pg4").feature("glob2").set("linestyle", "none");
    model.result("pg4").feature("glob2").set("linemarker", "point");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u53c2\u6570\u4f30\u8ba1 2");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u6e29\u5ea6");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").label("\u5217 3 (\u6a21\u578b)");
    model.result("pg5").feature("glob1").set("expr", new String[]{"lso3.col3c.model"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u5217 3 (\u6a21\u578b)"});
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "T");
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").label("\u5217 3 (\u6570\u636e)");
    model.result("pg5").feature("glob2").set("expr", new String[]{"lso3.col3c.data"});
    model.result("pg5").feature("glob2").set("descr", new String[]{"\u5217 3 (\u6570\u636e)"});
    model.result("pg5").feature("glob2").set("xdata", "expr");
    model.result("pg5").feature("glob2").set("xdataexpr", "T");
    model.result("pg5").feature("glob2").set("linestyle", "none");
    model.result("pg5").feature("glob2").set("linemarker", "point");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg1").run();

    model.study("std1").feature("lsqo").set("probewindow", "");

    model.result().remove("pg1");
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").set("innerinput", "last");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "tau", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().export().create("tbl1", "Table");
    model.result().export("tbl1").set("source", "evaluationgroup");
    model.result().export("tbl1").set("filename", "calibration_against_ttt_data_tau_calib.txt");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "calibration_against_ttt_data_tau_calib.txt");
    model.component("comp1").func("int1").setEntry("columnType", "col2", "none");
    model.component("comp1").func("int1").setEntry("columnType", "col3", "none");
    model.component("comp1").func("int1").setEntry("columnType", "col4", "none");
    model.component("comp1").func("int1").setEntry("columnType", "col5", "value");
    model.component("comp1").func("int1").setIndex("argunit", "K", 0);
    model.component("comp1").func("int1").setEntry("funcnames", "col5", "tau_calib");
    model.component("comp1").func("int1").setIndex("fununit", "s", 0);
    model.component("comp1").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").func("int1").set("extrap", "linear");

    model.component("comp1").physics().create("metp2", "GlobalMetalPhaseTransformation");

    model.study("std1").feature("time").setSolveFor("/physics/metp2", true);

    model.component("comp1").physics("metp2").feature("phase2").set("computetimes", true);
    model.component("comp1").physics("metp2").feature("phase2").setIndex("xitarget", 0, 2, 0);
    model.component("comp1").physics("metp2").feature("phase2").setIndex("xitarget", 0, 2, 0);
    model.component("comp1").physics("metp2").feature("phase2").setIndex("xitarget", 0.5, 1, 0);
    model.component("comp1").physics("metp2").feature("phase2").setIndex("xitarget", 0.9, 2, 0);
    model.component("comp1").physics("metp2").prop("Temperature").set("T", "T");
    model.component("comp1").physics("metp2").feature("ptran1").set("ptModel", "JMAK");
    model.component("comp1").physics("metp2").feature("ptran1").set("xieqjmak", "xieq");
    model.component("comp1").physics("metp2").feature("ptran1").set("taujmak", "tau_calib(metp2.T)");
    model.component("comp1").physics("metp2").feature("ptran1").set("njmak", "n");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/metp", true);
    model.study("std2").feature("time").setSolveFor("/physics/metp2", true);
    model.study("std1").feature("time").setSolveFor("/physics/metp2", false);
    model.study("std2").feature("time").set("tlist", "10^{range(log10(0.1),1/10,log10(100000))}");
    model.study("std2").feature("time").setSolveFor("/physics/metp", false);
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "xieq", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "", 0);
    model.study("std2").feature("time").setIndex("pname", "xieq", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "", 0);
    model.study("std2").feature("time").setIndex("pname", "T", 0);
    model.study("std2").feature("time").setIndex("plistarr", "range(853,10,1053)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol9").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"metp2.phase1.xi", "metp2.phase2.xi"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u76f8\u5206\u6570", "\u76f8\u5206\u6570"});
    model.result("pg1").label("\u76f8\u7ec4\u6210 (metp2)");
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u91d1\u76f8 1", "\u91d1\u76f8 2"});
    model.result("pg1").feature("glob1").set("title", "\u76f8\u7ec4\u6210");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob2").setIndex("expr", "metp2.T", 0);
    model.result("pg1").feature("glob2").set("linestyle", "dotted");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").label("\u76f8\u53d8\u56fe (metp2)");
    model.result("pg2").set("innerinput", "last");
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").set("xlabel", "\u65f6\u95f4");
    model.result("pg2").set("ylabel", "\u6e29\u5ea6");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", new String[]{"metp2.phase2.temperature_1"});
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "metp2.phase2.time_1");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u91d1\u76f8 2 0.01", 0);
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("expr", new String[]{"metp2.phase2.temperature_2"});
    model.result("pg2").feature("glob2").set("titletype", "none");
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "metp2.phase2.time_2");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "\u91d1\u76f8 2 0.5", 0);
    model.result("pg2").feature("glob2").set("linemarker", "cycle");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "all");
    model.result("pg2").create("glob3", "Global");
    model.result("pg2").feature("glob3").set("expr", new String[]{"metp2.phase2.temperature_3"});
    model.result("pg2").feature("glob3").set("titletype", "none");
    model.result("pg2").feature("glob3").set("xdata", "expr");
    model.result("pg2").feature("glob3").set("xdataexpr", "metp2.phase2.time_3");
    model.result("pg2").feature("glob3").set("legendmethod", "manual");
    model.result("pg2").feature("glob3").setIndex("legends", "\u91d1\u76f8 2 0.9", 0);
    model.result("pg2").feature("glob3").set("linemarker", "cycle");
    model.result("pg2").feature("glob3").set("markerpos", "datapoints");
    model.result("pg2").feature("glob3").set("xdatasolnumtype", "all");
    model.result("pg1").run();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u5b9e\u9a8c (1%)");
    model.result().table("tbl3").importData("calibration_against_ttt_data_ttt001.txt");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("\u5b9e\u9a8c (50%)");
    model.result().table("tbl4").importData("calibration_against_ttt_data_ttt050.txt");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").label("\u5b9e\u9a8c (90%)");
    model.result().table("tbl5").importData("calibration_against_ttt_data_ttt090.txt");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "all", 1);
    model.result("pg2").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg2").set("ylabel", "\u6e29\u5ea6 (degC)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "1%\u300150% \u548c 90% \u76f8\u53d8\u65f6\u7684 TTT \u56fe");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("table", "tbl3");
    model.result("pg2").feature("tblp1").set("xaxisdata", 2);
    model.result("pg2").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg2").feature("tblp1").set("plotcolumns", new int[]{1});
    model.result("pg2").feature("tblp1").set("linestyle", "none");
    model.result("pg2").feature("tblp1").set("linemarker", "cycle");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "1% (experimental)", 0);
    model.result("pg2").feature().duplicate("tblp2", "tblp1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").set("table", "tbl4");
    model.result("pg2").feature("tblp2").setIndex("legends", "50%\uff08\u5b9e\u9a8c\uff09", 0);
    model.result("pg2").feature().duplicate("tblp3", "tblp2");
    model.result("pg2").run();
    model.result("pg2").feature("tblp3").set("table", "tbl5");
    model.result("pg2").feature("tblp3").setIndex("legends", "90%\uff08\u5b9e\u9a8c\uff09", 0);
    model.result("pg2").run();

    model.title("\u6839\u636e TTT \u6570\u636e\u8fdb\u884c\u6821\u51c6");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u91d1\u5c5e\u52a0\u5de5\u6a21\u5757\u201d\u548c\u201c\u4f18\u5316\u6a21\u5757\u201d\u6839\u636e TTT\uff08\u7b49\u6e29\u8f6c\u53d8\uff09\u6570\u636e\u6765\u6821\u51c6\u76f8\u53d8\u6a21\u578b\u7684\u53c2\u6570\u3002");

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

    model.label("calibration_against_ttt_data.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
