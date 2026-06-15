/*
 * overdischarge_protection.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class overdischarge_protection {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("lb", "LumpedBattery");
    model.component("comp1").physics().create("lb2", "LumpedBattery");
    model.component("comp1").physics().create("cir", "Circuit");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/lb", true);
    model.study("std1").feature("time").setSolveFor("/physics/lb2", true);
    model.study("std1").feature("time").setSolveFor("/physics/cir", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("eta_IR_1C", "4.5[mV]", "1C \u6b27\u59c6\u8fc7\u7535\u4f4d");
    model.param().set("invJ0", "0.862", "\u9006\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41");
    model.param().set("tau", "1375[s]", "\u6269\u6563\u65f6\u95f4\u5e38\u6570");
    model.param().set("J0", "1/invJ0", "\u65e0\u91cf\u7eb2\u7535\u8377\u4ea4\u6362\u7535\u6d41");
    model.param().set("Q_cell0", "12[A*h]", "\u7535\u6c60\u5bb9\u91cf");
    model.param().set("SOC_0", "1", "\u521d\u59cb\u8377\u7535\u6001");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("C_rate", "0.5", "\u500d\u7387");
    model.param().set("I_1C", "Q_cell0/3600[s]", "1C \u653e\u7535");
    model.param().set("I_app", "I_1C*C_rate", "\u5916\u52a0\u7535\u6d41");
    model.param().set("t_stop", "(1[h]*SOC_window)/C_rate", "\u505c\u6b62\u65f6\u95f4");
    model.param().set("SOC_stop", "0.2", "\u6700\u4f4e SOC \u6c34\u5e73");
    model.param().set("SOC_window", "SOC_0-SOC_stop", "\u4eff\u771f\u7684 SOC \u7a97\u53e3");
    model.param().set("R1", "1e8[ohm]", "\u5e76\u8054\u7535\u963b\u503c");
    model.param().set("G_short", "0.5[S]", "\u77ed\u8def\u7535\u5bfc\u7387");
    model.param().set("t_short_start", "50[s]", "\u7535\u6c60\u77ed\u8def\u7684\u8d77\u59cb\u65f6\u95f4");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").label("\u77ed\u8def");
    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("E_OCP");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "overdischarge_protection_E_OCP.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("extrap", "interior");
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").setIndex("fununit", "V", 0);

    model.component("comp1").physics("lb").prop("BatterySettings").set("OperationMode", "CircuitTerminal");
    model.component("comp1").physics("lb").prop("BatterySettings").set("Q_cell0", "Q_cell0");
    model.component("comp1").physics("lb").prop("BatterySettings").set("SOC_cell0", "SOC_0");
    model.component("comp1").physics("lb").feature("cep1").set("OpenCircuitVoltageInput", "fromdef");
    model.component("comp1").physics("lb").feature("cep1").set("Eocvdef", "int1");
    model.component("comp1").physics("lb").feature("vl1").set("eta_ir1C", "eta_IR_1C");
    model.component("comp1").physics("lb").feature("vl1").set("J0", "J0");
    model.component("comp1").physics("lb").feature("vl1").set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("lb").feature("vl1").set("tau", "tau");
    model.component("comp1").physics("lb").create("isc1", "ShortCircuit", -1);
    model.component("comp1").physics("lb").feature("isc1").set("G_short", "G_short*step1((t-t_short_start)/1[s])");
    model.component("comp1").physics("lb2").prop("BatterySettings").set("OperationMode", "CircuitTerminal");
    model.component("comp1").physics("lb2").prop("BatterySettings").set("Q_cell0", "Q_cell0");
    model.component("comp1").physics("lb2").prop("BatterySettings").set("SOC_cell0", "SOC_0");
    model.component("comp1").physics("lb2").feature("cep1").set("OpenCircuitVoltageInput", "fromdef");
    model.component("comp1").physics("lb2").feature("cep1").set("Eocvdef", "int1");
    model.component("comp1").physics("lb2").feature("vl1").set("eta_ir1C", "eta_IR_1C");
    model.component("comp1").physics("lb2").feature("vl1").set("J0", "J0");
    model.component("comp1").physics("lb2").feature("vl1").set("IncludeConcentrationOverpotential", true);
    model.component("comp1").physics("lb2").feature("vl1").set("tau", "tau");
    model.component("comp1").physics("cir").create("I1", "CurrentSourceCircuit", -1);
    model.component("comp1").physics("cir").feature("I1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("I1").set("value", "I_app");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").label("\u96c6\u603b\u7535\u6c60 1");
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.lb.vl1.VCircuit");
    model.component("comp1").physics("cir").feature().duplicate("IvsU2", "IvsU1");
    model.component("comp1").physics("cir").feature("IvsU2").label("\u96c6\u603b\u7535\u6c60 2");
    model.component("comp1").physics("cir").feature("IvsU2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("IvsU2").set("V_src", "root.comp1.lb2.vl1.VCircuit");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "10[m\u03a9]");
    model.component("comp1").physics("cir").create("vm1", "VoltMeter", -1);
    model.component("comp1").physics("cir").feature("vm1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("vm1").setIndex("Connections", 3, 1, 0);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u65e0\u5206\u6d41\u5668");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,100,10000)");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").set("sweeptype", "filled");
    model.study("std1").feature("time").setIndex("pname", "eta_IR_1C", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "V", 0);
    model.study("std1").feature("time").setIndex("pname", "eta_IR_1C", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "V", 0);
    model.study("std1").feature("time").setIndex("pname", "C_rate", 0);
    model.study("std1").feature("time").setIndex("plistarr", "0.5 2", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.lb2.SOC_cell<=0.0", 0);

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("VoltMeter_cir_vm1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6c60\u7535\u538b");
    model.result("pg2").set("data", "none");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6c60\u7535\u4f4d (V)");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("data", "dset1");
    model.result("pg2").feature("glob1").set("expr", new String[]{"lb.E_cell"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u4f4d"});
    model.result("pg2").feature("glob1").setIndex("descr", "\u7535\u6c60 1", 0);
    model.result("pg2").feature("glob1").set("expr", new String[]{"lb.E_cell", "lb2.E_cell"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u6c60 1", "\u7535\u6c60\u7535\u4f4d"});
    model.result("pg2").feature("glob1").setIndex("descr", "\u7535\u6c60 2", 1);
    model.result("pg2").feature("glob1").set("expr", new String[]{"lb.E_cell", "lb2.E_cell", "cir.vm1.v"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60 1", "\u7535\u6c60 2", "\u7535\u538b\u8868\u201cvm1\u201d\u4e0a\u7684\u7535\u538b"});
    model.result("pg2").feature("glob1").setIndex("descr", "\u603b\u8ba1", 2);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u6c60\u7535\u6d41");
    model.result("pg3").set("ylabel", "\u7535\u6c60\u7535\u6d41 (A)");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("ymin", -30);
    model.result("pg3").set("ymax", 1);
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").set("expr", new String[]{"lb.I_cell"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u6d41"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg3").feature("glob1").setIndex("descr", "\u7535\u6c60 1", 0);
    model.result("pg3").feature("glob1").set("expr", new String[]{"lb.I_cell", "lb2.I_cell"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7535\u6c60 1", "\u7535\u6c60\u7535\u6d41"});
    model.result("pg3").feature("glob1").setIndex("descr", "\u7535\u6c60 2", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8377\u7535\u72b6\u6001");
    model.result("pg4").set("data", "none");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u8377\u7535\u72b6\u6001");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("data", "dset1");
    model.result("pg4").feature("glob1").set("expr", new String[]{"lb.SOC_cell"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u8377\u7535\u72b6\u6001"});
    model.result("pg4").feature("glob1").setIndex("descr", "\u7535\u6c60 1", 0);
    model.result("pg4").feature("glob1").set("expr", new String[]{"lb.SOC_cell", "lb2.SOC_cell"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u7535\u6c60 1", "\u7535\u6c60\u8377\u7535\u72b6\u6001"});
    model.result("pg4").feature("glob1").setIndex("descr", "\u7535\u6c60 2", 1);
    model.result("pg4").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg4").run();

    model.component("comp1").physics().create("ev", "Events");

    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.component("comp1").physics("ev").create("ds1", "DiscreteStates", -1);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dim", "Shunt_R1_on", 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 1, 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dim", "Shunt_R2_on", 1, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 0, 1, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimDescr", "", 1, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 1, 1, 0);
    model.component("comp1").physics("ev").create("is1", "IndicatorStates", -1);
    model.component("comp1").physics("ev").feature("is1").setIndex("indDim", "discharge_lb_1", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1")
         .setIndex("g", "(comp1.lb.SOC_cell-0.2)*(Shunt_R1_on==1)", 0, 0);
    model.component("comp1").physics("ev").feature("is1")
         .setIndex("dimDescr", "\u901a\u8fc7\u5206\u6d41\u5668 R1 \u653e\u7535", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("indDim", "discharge_lb_2", 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimDescr", "", 1, 0);
    model.component("comp1").physics("ev").feature("is1")
         .setIndex("g", "(comp1.lb2.SOC_cell-0.2)*(Shunt_R2_on==1)", 1, 0);
    model.component("comp1").physics("ev").feature("is1")
         .setIndex("dimDescr", "\u901a\u8fc7\u5206\u6d41\u5668 R2 \u653e\u7535", 1, 0);
    model.component("comp1").physics("ev").create("impl1", "ImplicitEvent", -1);
    model.component("comp1").physics("ev").feature("impl1").set("condition", "discharge_lb_1<0");
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitName", "Shunt_R1_on", 0, 0);
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitValue", "eps", 0, 0);
    model.component("comp1").physics("ev").feature().duplicate("impl2", "impl1");
    model.component("comp1").physics("ev").feature("impl2").set("condition", "discharge_lb_2<0");
    model.component("comp1").physics("ev").feature("impl2").setIndex("reInitName", "Shunt_R2_on", 0, 0);
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").label("\u5206\u6d41\u5668 R1");
    model.component("comp1").physics("cir").feature("R2").set("R", "R1*Shunt_R1_on");
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature().duplicate("R3", "R2");
    model.component("comp1").physics("cir").feature("R3").label("\u5206\u6d41\u5668 R2");
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R3").set("R", "R1*Shunt_R2_on");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/lb", true);
    model.study("std2").feature("time").setSolveFor("/physics/lb2", true);
    model.study("std2").feature("time").setSolveFor("/physics/cir", true);
    model.study("std2").feature("time").setSolveFor("/physics/ev", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u5206\u6d41\u5668");
    model.study("std2").feature("time").set("tlist", "range(0,100,10000)");
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "eta_IR_1C", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "V", 0);
    model.study("std2").feature("time").setIndex("pname", "eta_IR_1C", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "V", 0);
    model.study("std2").feature("time").setIndex("pname", "C_rate", 0);
    model.study("std2").feature("time").setIndex("plistarr", "0.5 2", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("VoltMeter_cir_vm1").genResult("none");

    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("glob2", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").set("data", "dset3");
    model.result("pg2").feature("glob2").set("linestyle", "dashed");
    model.result("pg2").feature("glob2").set("legend", false);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("data", "dset3");
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("legend", false);
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset3");
    model.result("pg4").feature("glob2").set("linestyle", "dashed");
    model.result("pg4").feature("glob2").set("legend", false);
    model.result("pg4").run();

    model.study("std1").feature("time").setSolveFor("/physics/ev", false);
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"cir/R2", "cir/R3"});

    model.title("\u4f7f\u7528\u5e76\u8054\u7535\u963b\u7684\u7535\u6c60\u8fc7\u653e\u7535\u4fdd\u62a4");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5c06\u591a\u4e2a\u201c\u96c6\u603b\u7535\u6c60\u201d\u6a21\u578b\u96c6\u6210\u5230\u201c\u7535\u8def\u201d\u63a5\u53e3\uff0c\u5c55\u793a\u4e86\u4e24\u4e2a\u7535\u6c60\u4e32\u8054\u8fde\u63a5\u7684\u60c5\u5f62\u3002\u6bcf\u4e2a\u7535\u6c60\u90fd\u7531\u4e00\u4e2a\u5e76\u8054\u7535\u963b\u4fdd\u62a4\uff0c\u5f53\u7535\u6c60\u8377\u7535\u72b6\u6001\u4f4e\u4e8e\u67d0\u4e2a\u9608\u503c\u65f6\uff0c\u8be5\u7535\u963b\u4fbf\u4f1a\u88ab\u6fc0\u6d3b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("overdischarge_protection.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
