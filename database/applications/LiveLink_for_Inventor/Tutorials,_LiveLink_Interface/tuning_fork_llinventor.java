/*
 * tuning_fork_llinventor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:42 by COMSOL 6.3.0.290. */
public class tuning_fork_llinventor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_Inventor\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

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

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkInventor");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();

    model.param().set("LL_d1", "78[mm]");

    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 1);
    model.study("std1").feature("eig").set("shift", "440[Hz]");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
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
    model.result("pg1").run();

    model.study("std1").create("opt", "Optimization");
    model.study("std1").feature("opt").set("optsolver", "bobyqa");
    model.study("std1").feature("opt").setIndex("optobj", "(freq-440[Hz])^2", 0);
    model.study("std1").feature("opt").setIndex("descr", "Frequency", 0);
    model.study("std1").feature("opt").setIndex("pname", "LL_d1", 0);
    model.study("std1").feature("opt").setIndex("initval", "78[mm]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("pname", "LL_d1", 0);
    model.study("std1").feature("opt").setIndex("initval", "78[mm]", 0);
    model.study("std1").feature("opt").setIndex("scale", 1, 0);
    model.study("std1").feature("opt").setIndex("lbound", "", 0);
    model.study("std1").feature("opt").setIndex("ubound", "", 0);
    model.study("std1").feature("opt").setIndex("scale", "10[mm]", 0);
    model.study("std1").feature("opt").setIndex("lbound", "70[mm]", 0);
    model.study("std1").feature("opt").setIndex("ubound", "81[mm]", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("o1").run("compute");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").label("\u632f\u578b (solid) 1");
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
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq1", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq1").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq1").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1) 1");
    model.result().evaluationGroup("std1EvgFrq1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq1").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq1").run();
    model.result("pg2").run();

    model.study("std1").feature("opt").set("probewindow", "");

    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"LL_d1"});
    model.result().numerical("gev1").set("descr", new String[]{"\u63a7\u5236\u53c2\u6570 LL_d1"});
    model.result().numerical("gev1").set("unit", new String[]{"mm"});
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").setResult();

    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(true, true);
    model.component("comp1").geom("geom1").run("fin");

    model.result("pg1").run();

    model.title("\u97f3\u53c9\u7684\u51e0\u4f55\u53c2\u6570\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u901a\u8fc7 LiveLink\u2122 \u63a5\u53e3\u4e0e Inventor\u00ae \u540c\u6b65\u7684\u97f3\u53c9\u7684\u57fa\u672c\u7279\u5f81\u9891\u7387\u548c\u7279\u5f81\u6a21\u6001\u3002\u7136\u540e\u4f18\u5316\u97f3\u53c9\u957f\u5ea6\uff0c\u4f7f\u97f3\u53c9\u53d1\u51fa\u97f3\u7b26 A (440\u00a0Hz)\u3002");

    model.label("tuning_fork_llinventor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
