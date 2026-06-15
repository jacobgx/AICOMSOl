/*
 * disc_resonator_anchor_losses.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:53 by COMSOL 6.3.0.290. */
public class disc_resonator_anchor_losses {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.param().set("R_d", "10[um]");
    model.param().descr("R_d", "\u5706\u76d8\u534a\u5f84");
    model.param().set("H_d", "3[um]");
    model.param().descr("H_d", "\u5706\u76d8\u9ad8\u5ea6");
    model.param().set("H_p", "0.8[um]");
    model.param().descr("H_p", "\u6746\u9ad8\u5ea6");
    model.param().set("R_p", "0.8[um]");
    model.param().descr("R_p", "\u6746\u534a\u5f84");
    model.param().set("R_s", "40[um]");
    model.param().descr("R_s", "\u57fa\u677f\u622a\u9762\u534a\u5f84");
    model.param().set("t_PML", "wl");
    model.param().descr("t_PML", "PML \u539a\u5ea6");
    model.param().set("wl", "9.5[um]");
    model.param().descr("wl", "\u58f0\u6ce2\u6ce2\u957f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R_d", "H_d"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "H_p"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"R_p", "2*H_d+2*H_p"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"2.5*R_p", "H_d"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "H_d+2*H_p"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R_s+t_PML");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "t_PML", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "R_s+-R_d", 1);
    model.component("comp1").geom("geom1").run("c1");

    model.component("comp1").coordSystem().create("pml1", "PML");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("pml1").selection().set(1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7845\u57fa\u677f");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"130[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.28"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2230[kg/m^3]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u591a\u6676\u7845");
    model.component("comp1").material("mat2").selection().set(4, 5, 6, 7, 9);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"150[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.22"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"2330[kg/m^3]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u91d1\u521a\u77f3");
    model.component("comp1").material("mat3").selection().set(8);
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"1061[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.12"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"3440[kg/m^3]"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(26);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 4, 5, 6, 7, 8, 9);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(25);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(5, 12, 13);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 4);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 1);
    model.study("std1").feature("eig").set("shift", "5e8");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().dataset("dset1solidrev").set("modenumber", "solid.mk");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u632f\u578b, 3D (solid)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup().create("std1mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std1mpf1").set("data", "dset1");
    model.result().evaluationGroup("std1mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormR", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cR \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLR", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cR \u5e73\u79fb", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1E-7");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "\u00b5m");
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormax", "6e-7");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "imag(-lambda)/(2*abs(real(lambda)))", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.title("\u76d8\u5f0f\u8c10\u632f\u5668\u7684\u951a\u635f\u8017");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u4e00\u4e2a\u94bb\u77f3\u76d8\u5f0f\u8c10\u632f\u5668\u4e2d\u7684\u951a\u635f\u53d7\u9650\u54c1\u8d28\u56e0\u5b50\u3002\u8c10\u632f\u5668\u901a\u8fc7\u591a\u6676\u7845\u67f1\u951a\u63a5\u5728\u57fa\u677f\u4e0a\uff0c\u529f\u7387\u901a\u8fc7\u8be5\u67f1\u4f20\u9012\u5230\u57fa\u677f\u3002\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u6765\u8868\u5f81\u65e0\u9650\u5927\u7684\u57fa\u677f\u3002\u6b64\u6a21\u578b\u57fa\u4e8e 2007 \u5e74\u5728\u6cd5\u56fd\u683c\u52d2\u8bfa\u5e03\u5c14\u4e3e\u529e\u7684 COMSOL \u7528\u6237\u5e74\u4f1a\u5206\u4f1a\u573a\u7528\u6237\u8bba\u6587 (P. Steeneken \"Parameter Extraction and Support-Loss in MEMS Resonators\", COMSOL Users Conference 2007, Grenoble)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("disc_resonator_anchor_losses.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
