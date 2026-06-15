/*
 * opamp_capacitive_load.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class opamp_capacitive_load {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Circuits");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("cir", "Circuit");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cir", true);

    model.param().set("OPAMP_RIN", "100[kohm]");
    model.param().descr("OPAMP_RIN", "\u8fd0\u7b97\u653e\u5927\u5668\u8f93\u5165\u7535\u963b");
    model.param().set("OPAMP_GAIN", "1e5");
    model.param().descr("OPAMP_GAIN", "\u8fd0\u7b97\u653e\u5927\u5668\u589e\u76ca");
    model.param().set("CLOAD", "10[nF]");
    model.param().descr("CLOAD", "\u7535\u5bb9\u8d1f\u8f7d");
    model.param().set("R1", "470[ohm]");
    model.param().descr("R1", "\u53cd\u9988\u7535\u963b 1");
    model.param().set("R2", "4700[ohm]");
    model.param().descr("R2", "\u53cd\u9988\u7535\u963b 2");
    model.param().set("OPAMP_P", "100[Hz]");
    model.param().descr("OPAMP_P", "\u8fd0\u7b97\u653e\u5927\u5668\u6781\u70b9\u9891\u7387");
    model.param().set("OPAMP_ROUT", "100[ohm]");
    model.param().descr("OPAMP_ROUT", "\u8fd0\u7b97\u653e\u5927\u5668\u8f93\u51fa\u7535\u963b");

    model.component("comp1").func().create("step1", "Step");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("VIN", ".5[V]*step1((t-.05[us])/1[us])");
    model.component("comp1").variable("var1").descr("VIN", "\u8f93\u5165\u7535\u538b");

    model.component("comp1").physics("cir").create("sub1", "SubCircuitBlock", -1);
    model.component("comp1").physics("cir").feature("sub1").label("OPAMP");
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "a", 2, 0);
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "a", 2, 0);
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "a", 3, 0);
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "a", 3, 0);
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "p", 0, 0);
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "n", 1, 0);
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "out", 2, 0);
    model.component("comp1").physics("cir").feature("sub1").setIndex("Connections", "gnd", 3, 0);
    model.component("comp1").physics("cir").feature("sub1").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("sub1").feature("R1").label("\u7535\u963b RIN");
    model.component("comp1").physics("cir").feature("sub1").feature("R1").tag("RIN");
    model.component("comp1").physics("cir").feature("sub1").feature("RIN").setIndex("Connections", "p", 0, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("RIN").setIndex("Connections", "n", 1, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("RIN").set("R", "OPAMP_RIN");
    model.component("comp1").physics("cir").feature("sub1").create("E1", "VoltageVoltageSource", -1);
    model.component("comp1").physics("cir").feature("sub1").feature("E1")
         .label("\u538b\u63a7\u7535\u538b\u6e90 EGAIN");
    model.component("comp1").physics("cir").feature("sub1").feature("E1").tag("EGAIN");
    model.component("comp1").physics("cir").feature("sub1").feature("EGAIN").setIndex("Connections", "gnd", 1, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("EGAIN").setIndex("Connections", "p", 2, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("EGAIN").setIndex("Connections", "n", 3, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("EGAIN").set("gain", "OPAMP_GAIN");
    model.component("comp1").physics("cir").feature("sub1").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("sub1").feature("R1").label("\u7535\u963b RP");
    model.component("comp1").physics("cir").feature("sub1").feature("R1").tag("RP");
    model.component("comp1").physics("cir").feature("sub1").feature("RP").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("RP").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("RP").set("R", "1/(2*pi*OPAMP_P*1[nF])");
    model.component("comp1").physics("cir").feature("sub1").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("sub1").feature("C1").label("\u7535\u5bb9\u5668 CP");
    model.component("comp1").physics("cir").feature("sub1").feature("C1").tag("CP");
    model.component("comp1").physics("cir").feature("sub1").feature("CP").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("CP").setIndex("Connections", "gnd", 1, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("CP").set("C", "1[nF]");
    model.component("comp1").physics("cir").feature("sub1").create("E1", "VoltageVoltageSource", -1);
    model.component("comp1").physics("cir").feature("sub1").feature("E1")
         .label("\u538b\u63a7\u7535\u538b\u6e90 EBUFFER");
    model.component("comp1").physics("cir").feature("sub1").feature("E1").tag("EBUFFER");
    model.component("comp1").physics("cir").feature("sub1").feature("EBUFFER").setIndex("Connections", "gnd", 1, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("EBUFFER").setIndex("Connections", 2, 2, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("EBUFFER").setIndex("Connections", "gnd", 3, 0);
    model.component("comp1").physics("cir").feature("sub1").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("sub1").feature("R1").label("\u7535\u963b ROUT");
    model.component("comp1").physics("cir").feature("sub1").feature("R1").tag("ROUT");
    model.component("comp1").physics("cir").feature("sub1").feature("ROUT").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("ROUT").setIndex("Connections", "out", 1, 0);
    model.component("comp1").physics("cir").feature("sub1").feature("ROUT").set("R", "OPAMP_ROUT");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").label("\u7535\u538b\u6e90 VIN");
    model.component("comp1").physics("cir").feature("V1").tag("VIN");
    model.component("comp1").physics("cir").feature("VIN").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("VIN").set("value", "VIN");
    model.component("comp1").physics("cir").create("X1", "SubCircuit", -1);
    model.component("comp1").physics("cir").feature("X1").label("\u5b50\u7535\u8def\u5b9e\u4f8b XOPAMP");
    model.component("comp1").physics("cir").feature("X1").tag("XOPAMP");
    model.component("comp1").physics("cir").feature("XOPAMP").set("subCircuitName", "sub1");
    model.component("comp1").physics("cir").feature("XOPAMP").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("XOPAMP").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("XOPAMP").setIndex("Connections", 3, 2, 0);
    model.component("comp1").physics("cir").feature("XOPAMP").setIndex("Connections", 0, 3, 0);
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R1");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "R2");
    model.component("comp1").physics("cir").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("C1").label("\u7535\u5bb9\u5668 CLOAD");
    model.component("comp1").physics("cir").feature("C1").tag("CLOAD");
    model.component("comp1").physics("cir").feature("CLOAD").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("CLOAD").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("CLOAD").set("C", "CLOAD");
    model.component("comp1").physics("cir").create("vm1", "VoltMeter", -1);
    model.component("comp1").physics("cir").feature("vm1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("vm1").setIndex("Connections", 0, 1, 0);

    model.study("std1").feature("time").set("tlist", "range(0,0.05[us],10[us])");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("VoltMeter_cir_vm1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.title("\u5e26\u7535\u5bb9\u8d1f\u8f7d\u7684\u8fd0\u7b97\u653e\u5927\u5668");

    model
         .description("\u8fd0\u7b97\u653e\u5927\u5668 (op-amp) \u662f\u4e00\u79cd\u5dee\u5206\u7535\u538b\u653e\u5927\u5668\uff0c\u5728\u6a21\u62df\u7535\u5b50\u65b9\u9762\u6709\u7740\u5e7f\u6cdb\u7684\u5e94\u7528\u3002\u672c\u6559\u7a0b\u5bf9\u8fde\u63a5\u5230\u53cd\u9988\u56de\u8def\u548c\u7535\u5bb9\u8d1f\u8f7d\u7684\u8fd0\u7b97\u653e\u5927\u5668\u5efa\u6a21\u3002\n\n\u8fd0\u7b97\u653e\u5927\u5668\u5728\u7535\u8def \u63a5\u53e3\u4e2d\u4f5c\u4e3a\u63d2\u5165\u5916\u90e8\u7535\u8def\u7684\u7b49\u6548\u7ebf\u6027\u5b50\u7535\u8def\u8fdb\u884c\u5efa\u6a21\u3002\u6a21\u578b\u7684\u4e00\u90e8\u5206\u57fa\u4e8e SPICE \u683c\u5f0f\uff0c\u4eff\u771f\u65f6\u95f4\u4e3a 10\u00a0ms\uff0c\u6bcf\u9694 0.05\u00a0ms \u8f93\u51fa\u4e00\u6b21\u6570\u636e\u3002\u8fd0\u7b97\u653e\u5927\u5668\u7684\u5185\u90e8\u52a8\u529b\u5b66\u4e0e\u53cd\u9988\u7f51\u7edc\u53d1\u751f\u76f8\u4e92\u4f5c\u7528\uff0c\u4ece\u800c\u4ea7\u751f\u8f93\u51fa\u4fe1\u53f7\u632f\u94c3\uff08\u9636\u8dc3\u54cd\u5e94\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("opamp_capacitive_load.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
