/*
 * activation_energy.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:29 by COMSOL 6.3.0.290. */
public class activation_energy {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Aex", "49", "\u901f\u7387\u5e38\u6570\u53c2\u6570");
    model.param().set("c_init_PhN2Cl", "1000[mol/m^3]", "PhN2Cl \u521d\u59cb\u6d53\u5ea6");
    model.param().set("E", "150e3[J/mol]", "\u6d3b\u5316\u80fd");
    model.param().set("T_iso", "313[K]", "\u6e29\u5ea6");

    model.component("comp1").physics("re").prop("energybalance").set("T", "T_iso");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "PhN2Cl=>PhCl+N2");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "c_init_PhN2Cl", 2, 0);

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").set("filename", "activation_energy_experiment313K.csv");
    model.component("comp1").common("lso1").importData();
    model.component("comp1").common("lso1").setEntry("modelExpression", "col2", "c_PhN2Cl");
    model.component("comp1").common("lso1").setEntry("variableName", "col2", "c_PhN2Cl");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col2", "re.c_PhN2Cl");
    model.component("comp1").common("lso1").setEntry("unit", "col2", "mol/m^3");
    model.component("comp1").common("lso1").setIndex("paramNames", "Aex", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso1").setIndex("paramNames", "Aex", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "", 0);
    model.component("comp1").common("lso1").setIndex("paramNames", "T_iso", 0);
    model.component("comp1").common("lso1").setIndex("paramExprs", "313[K]", 0);
    model.component("comp1").common("lso1").label("\u5b9e\u9a8c\u6570\u636e 313 K");
    model.component("comp1").common().duplicate("lso2", "lso1");
    model.component("comp1").common("lso2").label("\u5b9e\u9a8c\u6570\u636e 319 K");
    model.component("comp1").common("lso2").discardData();
    model.component("comp1").common("lso2").set("filename", "activation_energy_experiment319K.csv");
    model.component("comp1").common("lso2").importData();
    model.component("comp1").common("lso2").setIndex("paramExprs", "319[K]", 0);
    model.component("comp1").common().duplicate("lso3", "lso2");
    model.component("comp1").common("lso3").label("\u5b9e\u9a8c\u6570\u636e 323 K");
    model.component("comp1").common("lso3").discardData();
    model.component("comp1").common("lso3").set("filename", "activation_energy_experiment323K.csv");
    model.component("comp1").common("lso3").importData();
    model.component("comp1").common("lso3").setIndex("paramExprs", "323[K]", 0);
    model.component("comp1").common().duplicate("lso4", "lso3");
    model.component("comp1").common("lso4").label("\u5b9e\u9a8c\u6570\u636e 328 K");
    model.component("comp1").common("lso4").discardData();
    model.component("comp1").common("lso4").set("filename", "activation_energy_experiment328K.csv");
    model.component("comp1").common("lso4").importData();
    model.component("comp1").common("lso4").setIndex("paramExprs", "328[K]", 0);
    model.component("comp1").common().duplicate("lso5", "lso4");
    model.component("comp1").common("lso5").label("\u5b9e\u9a8c\u6570\u636e 333 K");
    model.component("comp1").common("lso5").discardData();
    model.component("comp1").common("lso5").set("filename", "activation_energy_experiment333K.csv");
    model.component("comp1").common("lso5").importData();
    model.component("comp1").common("lso5").setIndex("paramExprs", "333[K]", 0);

    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "exp(Aex)");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "E");

    model.study("std1").create("lsqo", "LSQOptimization");
    model.study("std1").feature("lsqo").setIndex("pname", "Aex", 0);
    model.study("std1").feature("lsqo").setIndex("initval", 49, 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "Aex", 0);
    model.study("std1").feature("lsqo").setIndex("initval", 49, 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "c_init_PhN2Cl", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1000[mol/m^3]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "c_init_PhN2Cl", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1000[mol/m^3]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 49, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", 10, 0);
    model.study("std1").feature("lsqo").setIndex("ubound", 100, 0);
    model.study("std1").feature("lsqo").setIndex("pname", "E", 1);
    model.study("std1").feature("lsqo").setIndex("scale", "150e3[J/mol]", 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "1e5", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "2e5", 1);
    model.study("std1").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_PhN2Cl", "re.c_PhCl", "re.c_N2"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u53c2\u6570\u4f30\u8ba1");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u65f6\u95f4");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").label("\u5217 2 (\u6a21\u578b)");
    model.result("pg2").feature("glob1").set("expr", new String[]{"lso1.c_PhN2Cl.model"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u5217 2 (\u6a21\u578b)"});
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "t");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").label("\u5217 2 (\u6570\u636e)");
    model.result("pg2").feature("glob2").set("expr", new String[]{"lso1.c_PhN2Cl.data"});
    model.result("pg2").feature("glob2").set("descr", new String[]{"\u5217 2 (\u6570\u636e)"});
    model.result("pg2").feature("glob2").set("xdata", "expr");
    model.result("pg2").feature("glob2").set("xdataexpr", "t");
    model.result("pg2").feature("glob2").set("linestyle", "none");
    model.result("pg2").feature("glob2").set("linemarker", "point");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u53c2\u6570\u4f30\u8ba1 1");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").label("\u5217 2 (\u6a21\u578b)");
    model.result("pg3").feature("glob1").set("expr", new String[]{"lso2.c_PhN2Cl.model"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u5217 2 (\u6a21\u578b)"});
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "t");
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").label("\u5217 2 (\u6570\u636e)");
    model.result("pg3").feature("glob2").set("expr", new String[]{"lso2.c_PhN2Cl.data"});
    model.result("pg3").feature("glob2").set("descr", new String[]{"\u5217 2 (\u6570\u636e)"});
    model.result("pg3").feature("glob2").set("xdata", "expr");
    model.result("pg3").feature("glob2").set("xdataexpr", "t");
    model.result("pg3").feature("glob2").set("linestyle", "none");
    model.result("pg3").feature("glob2").set("linemarker", "point");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linecolor", "cyclereset");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u53c2\u6570\u4f30\u8ba1 2");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u65f6\u95f4");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").label("\u5217 2 (\u6a21\u578b)");
    model.result("pg4").feature("glob1").set("expr", new String[]{"lso3.c_PhN2Cl.model"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u5217 2 (\u6a21\u578b)"});
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "t");
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").label("\u5217 2 (\u6570\u636e)");
    model.result("pg4").feature("glob2").set("expr", new String[]{"lso3.c_PhN2Cl.data"});
    model.result("pg4").feature("glob2").set("descr", new String[]{"\u5217 2 (\u6570\u636e)"});
    model.result("pg4").feature("glob2").set("xdata", "expr");
    model.result("pg4").feature("glob2").set("xdataexpr", "t");
    model.result("pg4").feature("glob2").set("linestyle", "none");
    model.result("pg4").feature("glob2").set("linemarker", "point");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u53c2\u6570\u4f30\u8ba1 3");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u65f6\u95f4");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").label("\u5217 2 (\u6a21\u578b)");
    model.result("pg5").feature("glob1").set("expr", new String[]{"lso4.c_PhN2Cl.model"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u5217 2 (\u6a21\u578b)"});
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "t");
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").label("\u5217 2 (\u6570\u636e)");
    model.result("pg5").feature("glob2").set("expr", new String[]{"lso4.c_PhN2Cl.data"});
    model.result("pg5").feature("glob2").set("descr", new String[]{"\u5217 2 (\u6570\u636e)"});
    model.result("pg5").feature("glob2").set("xdata", "expr");
    model.result("pg5").feature("glob2").set("xdataexpr", "t");
    model.result("pg5").feature("glob2").set("linestyle", "none");
    model.result("pg5").feature("glob2").set("linemarker", "point");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linecolor", "cyclereset");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u53c2\u6570\u4f30\u8ba1 4");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u65f6\u95f4");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").label("\u5217 2 (\u6a21\u578b)");
    model.result("pg6").feature("glob1").set("expr", new String[]{"lso5.c_PhN2Cl.model"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u5217 2 (\u6a21\u578b)"});
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "t");
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").label("\u5217 2 (\u6570\u636e)");
    model.result("pg6").feature("glob2").set("expr", new String[]{"lso5.c_PhN2Cl.data"});
    model.result("pg6").feature("glob2").set("descr", new String[]{"\u5217 2 (\u6570\u636e)"});
    model.result("pg6").feature("glob2").set("xdata", "expr");
    model.result("pg6").feature("glob2").set("xdataexpr", "t");
    model.result("pg6").feature("glob2").set("linestyle", "none");
    model.result("pg6").feature("glob2").set("linemarker", "point");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg1").run();

    model.study("std1").feature("lsqo").set("probewindow", "");

    model.result("pg2").run();
    model.result("pg2").label("\u53c2\u6570\u4f30\u8ba1 313 K");
    model.result("pg2").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg2").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u6d53\u5ea6 PhN<sub>2</sub>Cl (mol/m<sup>3</sup>)");
    model.result("pg2").set("legendlayout", "outside");
    model.result("pg2").set("legendposoutside", "top");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").label("313 K \u65f6\u7684\u4eff\u771f\u7ed3\u679c");
    model.result("pg2").feature("glob1").set("autoplotlabel", true);
    model.result("pg2").feature("glob1").set("autosolution", false);
    model.result("pg2").feature("glob1").set("autoexpr", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob2").label("313 K \u65f6\u7684\u5b9e\u9a8c\u7ed3\u679c");
    model.result("pg2").feature("glob2").set("autoplotlabel", true);
    model.result("pg2").feature("glob2").set("autosolution", false);
    model.result("pg2").feature("glob2").set("autoexpr", false);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u53c2\u6570\u4f30\u8ba1 319 K");
    model.result("pg3").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg3").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6d53\u5ea6 PhN<sub>2</sub>Cl (mol/m<sup>3</sup>)");
    model.result("pg3").set("legendlayout", "outside");
    model.result("pg3").set("legendposoutside", "top");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").label("319 K \u65f6\u7684\u4eff\u771f\u7ed3\u679c");
    model.result("pg3").feature("glob1").set("autoplotlabel", true);
    model.result("pg3").feature("glob1").set("autosolution", false);
    model.result("pg3").feature("glob1").set("autoexpr", false);
    model.result("pg3").run();
    model.result("pg3").feature("glob2").label("319 K \u65f6\u7684\u5b9e\u9a8c\u7ed3\u679c");
    model.result("pg3").feature("glob2").set("autoplotlabel", true);
    model.result("pg3").feature("glob2").set("autosolution", false);
    model.result("pg3").feature("glob2").set("autoexpr", false);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u53c2\u6570\u4f30\u8ba1 323 K");
    model.result("pg4").setIndex("looplevelinput", "manual", 1);
    model.result("pg4").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg4").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6d53\u5ea6 PhN<sub>2</sub>Cl (mol/m<sup>3</sup>)");
    model.result("pg4").set("legendlayout", "outside");
    model.result("pg4").set("legendposoutside", "top");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").label("323 K \u65f6\u7684\u4eff\u771f\u7ed3\u679c");
    model.result("pg4").feature("glob1").set("autoplotlabel", true);
    model.result("pg4").feature("glob1").set("autosolution", false);
    model.result("pg4").feature("glob1").set("autoexpr", false);
    model.result("pg4").run();
    model.result("pg4").feature("glob2").label("323 K \u65f6\u7684\u5b9e\u9a8c\u7ed3\u679c");
    model.result("pg4").feature("glob2").set("autoplotlabel", true);
    model.result("pg4").feature("glob2").set("autosolution", false);
    model.result("pg4").feature("glob2").set("autoexpr", false);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u53c2\u6570\u4f30\u8ba1 328 K");
    model.result("pg5").setIndex("looplevelinput", "manual", 1);
    model.result("pg5").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg5").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6d53\u5ea6 PhN<sub>2</sub>Cl (mol/m<sup>3</sup>)");
    model.result("pg5").set("legendlayout", "outside");
    model.result("pg5").set("legendposoutside", "top");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").label("328 K \u65f6\u7684\u4eff\u771f\u7ed3\u679c");
    model.result("pg5").feature("glob1").set("autoplotlabel", true);
    model.result("pg5").feature("glob1").set("autosolution", false);
    model.result("pg5").feature("glob1").set("autoexpr", false);
    model.result("pg5").run();
    model.result("pg5").feature("glob2").label("328 K \u65f6\u7684\u5b9e\u9a8c\u7ed3\u679c");
    model.result("pg5").feature("glob2").set("autoplotlabel", true);
    model.result("pg5").feature("glob2").set("autosolution", false);
    model.result("pg5").feature("glob2").set("autoexpr", false);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").label("\u53c2\u6570\u4f30\u8ba1 333 K");
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", new int[]{5}, 1);
    model.result("pg6").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u6d53\u5ea6 PhN<sub>2</sub>Cl (mol/m<sup>3</sup>)");
    model.result("pg6").set("legendlayout", "outside");
    model.result("pg6").set("legendposoutside", "top");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").label("333 K \u65f6\u7684\u4eff\u771f\u7ed3\u679c");
    model.result("pg6").feature("glob1").set("autoplotlabel", true);
    model.result("pg6").feature("glob1").set("autosolution", false);
    model.result("pg6").feature("glob1").set("autoexpr", false);
    model.result("pg6").run();
    model.result("pg6").feature("glob2").label("333 K \u65f6\u7684\u5b9e\u9a8c\u7ed3\u679c");
    model.result("pg6").feature("glob2").set("autoplotlabel", true);
    model.result("pg6").feature("glob2").set("autosolution", false);
    model.result("pg6").feature("glob2").set("autoexpr", false);
    model.result("pg6").run();
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result("pg2").run();

    model
         .title("\u4f7f\u7528\u53c2\u6570\u4f30\u8ba1\u786e\u5b9a\u52a8\u529b\u5b66\u963f\u7d2f\u5c3c\u4e4c\u65af\u53c2\u6570");

    model
         .description("\u53c2\u6570\u4f30\u8ba1\u5728\u7406\u8bba\u6a21\u578b\u7684\u5b9e\u9a8c\u6821\u51c6\u4e2d\u81f3\u5173\u91cd\u8981\u3002\u672c\u6a21\u578b\u4f7f\u7528\u53c2\u6570\u4f30\u8ba1\u529f\u80fd\u548c\u591a\u4e2a\u5b9e\u9a8c\u6570\u636e\u8f93\u5165\u6587\u4ef6\uff0c\u786e\u5b9a\u6c2f\u5316\u91cd\u6c2e\u82ef\u5206\u89e3\u4e3a\u6c2f\u5316\u82ef\u548c\u6c2e\u6c14\u7684\u4e00\u7ea7\u53cd\u5e94\u963f\u7d2f\u5c3c\u4e4c\u65af\u53c2\u6570\u3002");

    model.label("activation_energy.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
