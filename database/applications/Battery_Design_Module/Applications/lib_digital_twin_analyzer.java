/*
 * lib_digital_twin_analyzer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:42 by COMSOL 6.3.0.290. */
public class lib_digital_twin_analyzer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("lb", "LumpedBattery");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/lb", true);

    model.param().label("Parameters for Digital Twin Analyzer");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("eta_IR_1C", "40[mV]", "Ohmic overpotential at 1C, fitting parameter");
    model.param().set("tau", "1100[s]", "Diffusion time constant, fitting parameter");
    model.param().set("Q_cell0", "14400[C]", "Battery cell capacity");
    model.param().set("T", "298.15[K]", "Temperature");
    model.param().set("N_series", "9", "Battery cells in series");
    model.param().set("N_parallel", "2", "Battery cells in parallel");
    model.param().set("SOC_min", "0.2", "Minimum SOC allowed before charge");
    model.param().set("eta_IR_1C_EOL", "55[mV]", "Ohmic overpotential at 1C at EOL");
    model.param().set("tau_EOL", "5000[s]", "Diffusion time constant at EOL");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("Variables - Battery Tracking and Use");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("I_battery_gen", "I_battery_gen(t)[A]", "Battery current from generator");
    model.component("comp1").variable("var1")
         .set("E_battery_gen", "E_battery_gen(t)[V]", "Battery voltage from generator");
    model.component("comp1").variable("var1")
         .set("I_cell_gen", "I_battery_gen/N_parallel", "Cell current from generator");
    model.component("comp1").variable("var1")
         .set("E_cell_gen", "E_battery_gen/N_series", "Cell voltage from generator");
    model.component("comp1").variable("var1")
         .set("SOC0", "E_OCP_inv(E_cell0)", "Initial cell SOC, assuming fully relaxed initially");
    model.component("comp1").variable("var1")
         .set("E_cell0", "E_battery_gen(0)[V]/N_series", "Initial cell voltage, assuming fully relaxed initially");
    model.component("comp1").variable("var1")
         .set("Charge_trigger", "if(lb.SOC_cell<SOC_min,1,0)", "Trigger for charge");
    model.component("comp1").variable("var1")
         .set("EOL_trigger", "if(eta_IR_1C>eta_IR_1C_EOL||tau>tau_EOL,1,0)", "Trigger for battery end of life");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("Interpolation - OCP Curve");
    model.component("comp1").func("int1").set("funcname", "E_OCP");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0", "1.3104190837885064"}, 
         {"0.01", "2.2611069048882873"}, 
         {"0.02", "2.8334213300309643"}, 
         {"0.030000000000000002", "3.1844362423678003"}, 
         {"0.04", "3.393016295869783"}, 
         {"0.05", "3.510875145406253"}, 
         {"0.060000000000000005", "3.5869463584273378"}, 
         {"0.07", "3.6264514929037994"}, 
         {"0.08", "3.6557565420023983"}, 
         {"0.09000000000000001", "3.6850615911009976"}, 
         {"0.1", "3.6994551120045407"}, 
         {"0.11", "3.7113345164250635"}, 
         {"0.12000000000000001", "3.7232139208455868"}, 
         {"0.13", "3.7350933252661096"}, 
         {"0.14", "3.746972729686633"}, 
         {"0.15", "3.7588394760123167"}, 
         {"0.16", "3.7669805471052284"}, 
         {"0.17", "3.7751216181981406"}, 
         {"0.18000000000000002", "3.7832626892910524"}, 
         {"0.19", "3.7914037603839645"}, 
         {"0.2", "3.7995448314768763"}, 
         {"0.21000000000000002", "3.8076859025697884"}, 
         {"0.22", "3.8158269736627"}, 
         {"0.23", "3.8239680447556124"}, 
         {"0.24000000000000002", "3.832109115848524"}, 
         {"0.25", "3.8402384951548894"}, 
         {"0.26", "3.844926614135922"}, 
         {"0.27", "3.8496147331169555"}, 
         {"0.28", "3.8543028520979887"}, 
         {"0.29000000000000004", "3.8589909710790216"}, 
         {"0.3", "3.863679090060055"}, 
         {"0.31", "3.8683672090410877"}, 
         {"0.32", "3.873055328022121"}, 
         {"0.33", "3.877743447003154"}, 
         {"0.34", "3.882431565984187"}, 
         {"0.35000000000000003", "3.8871182387192977"}, 
         {"0.36000000000000004", "3.891379235814907"}, 
         {"0.37", "3.8956402329105164"}, 
         {"0.38", "3.900108284697505"}, 
         {"0.39", "3.9048921849677303"}, 
         {"0.4", "3.910004858946812"}, 
         {"0.41000000000000003", "3.9155565350495363"}, 
         {"0.42000000000000004", "3.9213824207219656"}, 
         {"0.43", "3.927676048105941"}, 
         {"0.44", "3.9340438371441717"}, 
         {"0.45", "3.94051108662678"}, 
         {"0.46", "3.9470234377800457"}, 
         {"0.47000000000000003", "3.9533348394394214"}, 
         {"0.48000000000000004", "3.9592735197022777"}, 
         {"0.49000000000000005", "3.9650124251831564"}, 
         {"0.5", "3.970366661250179"}, 
         {"0.51", "3.975134457606617"}, 
         {"0.52", "3.979408469784772"}, 
         {"0.53", "3.9832924969139087"}, 
         {"0.54", "3.986400783870235"}, 
         {"0.55", "3.989209391104789"}, 
         {"0.56", "3.9920179983393425"}, 
         {"0.5700000000000001", "3.994805300205646"}, 
         {"0.5800000000000001", "3.9971563232603926"}, 
         {"0.5900000000000001", "3.9995073463151387"}, 
         {"0.6", "4.001858369369885"}, 
         {"0.61", "4.004209392424632"}, 
         {"0.62", "4.006560415479378"}, 
         {"0.63", "4.008911438534124"}, 
         {"0.64", "4.011262461588871"}, 
         {"0.65", "4.014107842011973"}, 
         {"0.66", "4.017168121841405"}, 
         {"0.67", "4.020228401670838"}, 
         {"0.68", "4.02328868150027"}, 
         {"0.6900000000000001", "4.0263489613297025"}, 
         {"0.7000000000000001", "4.029409241159136"}, 
         {"0.7100000000000001", "4.032469520988568"}, 
         {"0.7200000000000001", "4.03470071596249"}, 
         {"0.73", "4.035374828848886"}, 
         {"0.74", "4.036048941735283"}, 
         {"0.75", "4.036723054621679"}, 
         {"0.76", "4.037397167508075"}, 
         {"0.77", "4.038071280394472"}, 
         {"0.78", "4.038745393280867"}, 
         {"0.79", "4.039419506167263"}, 
         {"0.8", "4.039965701342635"}, 
         {"0.81", "4.040511627548762"}, 
         {"0.8200000000000001", "4.041057553754889"}, 
         {"0.8300000000000001", "4.041603479961017"}, 
         {"0.8400000000000001", "4.042149406167145"}, 
         {"0.8500000000000001", "4.042695332373272"}, 
         {"0.86", "4.0432412585794"}, 
         {"0.87", "4.04546697590993"}, 
         {"0.88", "4.048603777764223"}, 
         {"0.89", "4.0517405796185155"}, 
         {"0.9", "4.054877381472807"}, 
         {"0.91", "4.0580141833271"}, 
         {"0.92", "4.061150985181393"}, 
         {"0.93", "4.064287787035685"}, 
         {"0.9400000000000001", "4.069434668295399"}, 
         {"0.9500000000000001", "4.079298736570241"}, 
         {"0.9600000000000001", "4.091601063364331"}, 
         {"0.9700000000000001", "4.110991916734893"}, 
         {"0.9800000000000001", "4.13395738036637"}, 
         {"0.9900000000000001", "4.167078533171239"}, 
         {"1", "4.204442586248173"}});
    model.component("comp1").func("int1").setIndex("fununit", "V", 0);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").set("defineinv", true);
    model.component("comp1").func("int1").set("funcinvname", "E_OCP_inv");
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("Interpolation - E and I vs. t");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("Table - Battery Data Package");
    model.result().table("tbl1").importData("lib_digital_twin_analyzer_embedded_pe_initializer.txt");

    model.component("comp1").func("int2").set("source", "resultTable");
    model.component("comp1").func("int2").setIndex("argunit", "s", 0);
    model.component("comp1").func("int2").setEntry("columnType", "col2", "none");
    model.component("comp1").func("int2").setEntry("columnType", "col3", "value");
    model.component("comp1").func("int2").setEntry("funcnames", "col3", "E_battery_gen");
    model.component("comp1").func("int2").setEntry("columnType", "col4", "value");
    model.component("comp1").func("int2").setEntry("funcnames", "col4", "I_battery_gen");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("Global Variable Probe - Ohmic Overpotential");
    model.component("comp1").probe("var1").set("expr", "eta_IR_1C");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "Ohmic overpotential at 1C");
    model.component("comp1").probe().duplicate("var2", "var1");
    model.component("comp1").probe("var2").label("Global Variable Probe - Diffusion Time Constant");
    model.component("comp1").probe("var2").set("expr", "tau");
    model.component("comp1").probe("var2").set("descr", "Diffusion time constant");

    model.component("comp1").physics("lb").prop("BatterySettings").set("I_app", "I_cell_gen");
    model.component("comp1").physics("lb").prop("BatterySettings").set("Q_cell0", "Q_cell0");
    model.component("comp1").physics("lb").prop("BatterySettings").set("SOC_cell0", "SOC0");
    model.component("comp1").physics("lb").feature("cep1").set("OpenCircuitVoltageInput", "fromdef");
    model.component("comp1").physics("lb").feature("cep1").set("Eocvdef", "int1");
    model.component("comp1").physics("lb").feature("cep1").set("Tref", "T");
    model.component("comp1").physics("lb").feature("vl1").set("minput_temperature", "T");
    model.component("comp1").physics("lb").feature("vl1").set("eta_ir1C", "eta_IR_1C");
    model.component("comp1").physics("lb").feature("vl1").set("IncludeActivationOverpotential", false);
    model.component("comp1").physics("lb").feature("vl1").set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("lb").feature("vl1").set("tau", "tau");

    model.study("std1").label("Study - Parameter Estimation");
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").create("lsqo", "LSQOptimization");
    model.study("std1").feature("lsqo").set("source", "resultTable");
    model.study("std1").feature("lsqo").setEntry("columnType", "col2", "none");
    model.study("std1").feature("lsqo").setEntry("modelExpression", "col3", "comp1.lb.E_cell*N_series");
    model.study("std1").feature("lsqo").setEntry("columnType", "col4", "none");
    model.study("std1").feature("lsqo").setIndex("pname", "eta_IR_1C", 0);
    model.study("std1").feature("lsqo").setIndex("initval", "40[mV]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "eta_IR_1C", 0);
    model.study("std1").feature("lsqo").setIndex("initval", "40[mV]", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std1").feature("lsqo").setIndex("scale", 0.01, 0);
    model.study("std1").feature("lsqo").setIndex("lbound", "10[mV]", 0);
    model.study("std1").feature("lsqo").setIndex("ubound", "1[V]", 0);
    model.study("std1").feature("lsqo").setIndex("pname", "tau", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1100[s]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("pname", "tau", 1);
    model.study("std1").feature("lsqo").setIndex("initval", "1100[s]", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std1").feature("lsqo").setIndex("scale", 1000, 1);
    model.study("std1").feature("lsqo").setIndex("lbound", "1000[s]", 1);
    model.study("std1").feature("lsqo").setIndex("ubound", "10000[s]", 1);
    model.study("std1").feature("lsqo").set("opttolinner", 0.1);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.study("std1").feature("lsqo").set("probewindow", "");

    model.sol("sol1").clearSolutionData();

    model.study("std1").feature("lsqo").set("plot", true);

    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("Global Evaluation - EOL Trigger");
    model.result().numerical("gev3").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev3").set("expr", new String[]{"EOL_trigger"});
    model.result().numerical("gev3").set("descr", new String[]{"Trigger for battery end of life"});
    model.result().numerical("gev3").set("unit", new String[]{""});
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").label("Global Evaluation - Charge Trigger");
    model.result().numerical("gev4").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev4").set("expr", new String[]{"Charge_trigger"});
    model.result().numerical("gev4").set("descr", new String[]{"Trigger for charge"});
    model.result().numerical("gev4").set("unit", new String[]{""});
    model.result().numerical().create("gev5", "EvalGlobal");
    model.result().numerical("gev5").label("Global Evaluation - End SOC");
    model.result().numerical("gev5").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev5").set("expr", new String[]{"lb.SOC_cell"});
    model.result().numerical("gev5").set("descr", new String[]{"Cell state of charge"});
    model.result().numerical("gev5").set("unit", new String[]{"1"});
    model.result().numerical().create("gev6", "EvalGlobal");
    model.result().numerical("gev6").label("Global Evaluation - Historic Ohmic Overpotential");
    model.result().numerical("gev6").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev6").set("expr", new String[]{"eta_IR_1C"});
    model.result().numerical("gev6").set("descr", new String[]{"Ohmic overpotential at 1C, fitting parameter"});
    model.result().numerical("gev6").set("unit", new String[]{"V"});
    model.result().numerical("gev6").setIndex("unit", "mV", 0);
    model.result().numerical().create("gev7", "EvalGlobal");
    model.result().numerical("gev7").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev7").set("expr", new String[]{"tau"});
    model.result().numerical("gev7").set("descr", new String[]{"Diffusion time constant, fitting parameter"});
    model.result().numerical("gev7").set("unit", new String[]{"s"});
    model.result().numerical("gev7").label("Global Evaluation - Historic Diffusion Time Constant");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("Table - Historic Data");
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").label("Table - Full Cycle Data");
    model.result().table("tbl5").set("tablebuffersize", 10000000);
    model.result().table("tbl1").clearTableData();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("Battery Data Package Information");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Time (s)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Voltage (V)");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("yseclabel", "Current (A)");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").set("legendposoutside", "top");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature().remove("tblp1");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").setIndex("expr", "comp1.lb.E_cell*N_series", 0);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "Modeled voltage", 0);
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").setIndex("expr", "lb.Eocv_cell*N_series", 0);
    model.result("pg1").feature("glob2").setIndex("legends", "Modeled OCP", 0);
    model.result("pg1").feature().duplicate("glob3", "glob2");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("glob3").set("expr", new String[]{"E_battery_gen"});
    model.result("pg1").feature("glob3").set("descr", new String[]{"Battery voltage from generator"});
    model.result("pg1").feature("glob3").set("unit", new String[]{""});
    model.result("pg1").feature("glob3").setIndex("legends", "Data package voltage", 0);
    model.result("pg1").feature().duplicate("glob4", "glob3");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("glob4").set("plotonsecyaxis", true);
    model.result("pg1").feature("glob4").set("expr", new String[]{"I_battery_gen"});
    model.result("pg1").feature("glob4").set("descr", new String[]{"Battery current from generator"});
    model.result("pg1").feature("glob4").set("unit", new String[]{""});
    model.result("pg1").feature("glob4").setIndex("legends", "Current", 0);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Historic Information eta");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("table", "tbl4");
    model.result("pg2").feature().duplicate("tblp2", "tblp1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp2").set("preprocy", "linear");
    model.result("pg2").feature("tblp2").set("scalingy", 0);
    model.result("pg2").feature("tblp2").set("shifty", 55);
    model.result("pg2").feature("tblp2").set("linestyle", "dashed");
    model.result("pg2").feature("tblp2").set("linecolor", "black");
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Data package");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\\eta<sub>IR, 1C</sub> (mV)");
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("Historic Information tau");
    model.result("pg3").set("ylabel", "\\tau  (s)");
    model.result("pg3").run();
    model.result("pg3").feature("tblp2").set("shifty", "5000[s]");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("Historic Information End SOC");
    model.result("pg4").set("ylabel", "End SOC (1)");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("shifty", 1);
    model.result("pg4").feature().duplicate("tblp3", "tblp2");
    model.result("pg4").run();
    model.result("pg4").feature("tblp3").set("shifty", 0.2);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("Accumulated Data Packages");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "Time (s)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "Voltage (V)");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").set("yseclabelactive", true);
    model.result("pg5").set("yseclabel", "Current (A)");
    model.result("pg5").set("legendlayout", "outside");
    model.result("pg5").set("legendposoutside", "top");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("table", "tbl5");
    model.result("pg5").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("legendmethod", "manual");
    model.result("pg5").feature("tblp1").setIndex("legends", "Voltage", 0);
    model.result("pg5").feature().duplicate("tblp2", "tblp1");
    model.result("pg5").run();
    model.result("pg5").feature("tblp2").set("plotonsecyaxis", true);
    model.result("pg5").feature("tblp2").setIndex("legends", "Current", 0);

    model.title(null);

    model.description("");

    model.label("lib_digital_twin_analyzer_embedded.mph");

    model.result("pg5").run();
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///lib_digital_twin_analyzer.docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature().create("var1", "Variables");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature().create("std1", "Study");
    model.result().report("rpt1").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec4").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec1").set("imagesize", "large");
    model.result().report("rpt1").feature("sec4").feature("sec1").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec4").feature("sec2").feature("pg1").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec4").feature().duplicate("sec3", "sec2");
    model.result().report("rpt1").feature("sec4").feature("sec3").feature("pg1").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec4").feature().duplicate("sec4", "sec3");
    model.result().report("rpt1").feature("sec4").feature("sec4").feature("pg1").set("noderef", "pg4");
    model.result().report("rpt1").feature("sec4").feature().duplicate("sec5", "sec4");
    model.result().report("rpt1").feature("sec4").feature("sec5").set("imagesize", "large");
    model.result().report("rpt1").feature("sec4").feature("sec5").feature("pg1").set("noderef", "pg5");

    model.title("\u7535\u52a8\u6ed1\u677f\u8f66\u7535\u6c60\u7684\u6570\u5b57\u5b6a\u751f\u5206\u6790\u5668");

    model
         .description("\u672c App \u662f\u7535\u52a8\u6ed1\u677f\u8f66\u7535\u6c60\u7684\u6570\u5b57\u5b6a\u751f\u6a21\u578b\uff0c\u4e0e\u201c\u7535\u52a8\u6ed1\u677f\u8f66\u7535\u6c60\u7684\u6570\u5b57\u5b6a\u751f\u6a21\u62df\u5668\u201dApp \u4e00\u8d77\u8fd0\u884c\uff0c\u7528\u4e8e\u6536\u96c6\u7535\u6c60\u6570\u636e\uff0c\u5e76\u4f7f\u7528\u201c\u53c2\u6570\u4f30\u8ba1\u201d\u6765\u8bc4\u4f30\u7535\u6c60\u7684\u5065\u5eb7\u72b6\u6001 (SOH) \u548c\u8377\u7535\u72b6\u6001 (SOC)\u3002");

    model.label("lib_digital_twin_analyzer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
