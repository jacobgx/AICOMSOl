/*
 * random_vibration_deep_beam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:25 by COMSOL 6.3.0.290. */
public class random_vibration_deep_beam {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("beam", "HermitianBeam", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").setSolveFor("/physics/beam", true);
    model.study().create("std2");
    model.study("std2").create("mr", "ModelReduction");
    model.study("std2").feature("mr").setSolveFor("/physics/beam", true);

    model.common().create("grmi1", "GlobalReducedModelInputs", "");

    model.reduced().create("rom1", "ModalFrequency");
    model.reduced().create("rvib1", "RandomVibration");
    model.reduced("rvib1").set("frequencyResponseModel", "rom1");

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 12);
    model.study("std1").feature("eig").set("shiftactive", true);
    model.study("std1").feature("eig").set("shift", "1");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").feature("mr").set("trainingStudy", "std1");
    model.study("std2").feature("mr").set("trainingStep", "eig");
    model.study("std2").feature("mr").feature().create("freq1", "Frequency");
    model.study("std2").feature("mr").feature("freq1").set("plist", "100");
    model.study("std2").feature("mr").set("unreducedModelStudy", "std2");
    model.study("std2").feature("mr").set("unreducedModelStep", "freq1");
    model.study("std2").feature("mr").set("romdata", "rom1");

    model.param().set("F", "1e6[N/m]");
    model.param().descr("F", "\u8fb9\u8f7d\u8377");
    model.param().set("PSD", "F^2/1[Hz]");
    model.param().descr("PSD", "\u968f\u673a\u8fb9\u8f7d\u8377\uff0c\u529f\u7387\u8c31\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{10, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"2e11"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8000"});

    model.component("comp1").physics("beam").prop("BeamFormulation").set("BeamFormulation", "Timoshenko");
    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "RectangularSection");
    model.component("comp1").physics("beam").feature("csd1").set("hy_rect", 2);
    model.component("comp1").physics("beam").feature("csd1").set("hz_rect", 2);
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{0, 0, 1});
    model.component("comp1").physics("beam").feature("emm1").create("dmp1", "Damping", 1);
    model.component("comp1").physics("beam").feature("emm1").feature("dmp1").set("alpha_dM", 5.36);
    model.component("comp1").physics("beam").feature("emm1").feature("dmp1").set("beta_dK", "7.46e-5");
    model.component("comp1").physics("beam").create("pdr1", "DispRot0", 0);
    model.component("comp1").physics("beam").feature("pdr1").selection().set(1);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("beam").feature("pdr1").set("RotationType", "RotationGroup");
    model.component("comp1").physics("beam").feature("pdr1").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("FreeRotationAround", true, 2);
    model.component("comp1").physics("beam").create("pdr2", "DispRot0", 0);
    model.component("comp1").physics("beam").feature("pdr2").selection().set(2);
    model.component("comp1").physics("beam").feature("pdr2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("beam").feature("pdr2").setIndex("Direction", "prescribed", 2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext1").selection().set(1);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"5", "0", "0"});
    model.component("comp1").cpl("genext1").set("srcframe", "material");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("V", "genext1(v)");
    model.component("comp1").variable("var1").descr("V", "\u4f4d\u79fb\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1").set("Sb", "genext1(beam.sb1)");
    model.component("comp1").variable("var1").descr("Sb", "\u5f2f\u66f2\u5e94\u529b");

    model.common("grmi1").setIndex("name", "Fy", 0);
    model.common("grmi1").setIndex("expression", "F", 0);

    model.component("comp1").physics("beam").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("beam").feature("el1").selection().set(1);
    model.component("comp1").physics("beam").feature("el1")
         .set("forceReferenceLength", new String[]{"0", "Fy", "0"});

    model.study("std1").feature("eig").set("shift", "40");
    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"beam/emm1/dmp1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"beam.disp"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u632f\u578b (beam)");
    model.result("pg1").feature("line1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "beam.re");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
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

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.reduced("rvib1").setIndex("powerSpectralDensity", "PSD", 0);

    model.sol("sol2").updateSolution();

    model.result().numerical().create("gevs1", "EvalGlobalSweep");
    model.result().numerical("gevs1").setIndex("pname", "freq", 0);
    model.result().numerical("gevs1")
         .setIndex("plistarr", "range(20,1,41) range(41.5,0.01,43.5) range(44,1,60) [Hz]", 0);
    model.result().numerical("gevs1").setIndex("expr", "rvib1.psd(V)", 0);
    model.result().numerical("gevs1").setIndex("unit", "mm^2/Hz", 0);
    model.result().numerical("gevs1").setIndex("descr", "\u968f\u673a\u54cd\u5e94 PSD", 0);
    model.result().numerical("gevs1").setIndex("expr", "abs(rom1.eval(V))^2", 1);
    model.result().numerical("gevs1").setIndex("unit", "mm^2", 1);
    model.result().numerical("gevs1").setIndex("descr", "\u9891\u7387\u54cd\u5e94\u7684\u5e73\u65b9", 1);
    model.result().numerical("gevs1").set("data", "dset2");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97\u626b\u63cf 1");
    model.result().numerical("gevs1").set("table", "tbl1");
    model.result().numerical("gevs1").setResult();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("source", "table");
    model.result("pg2").feature("tblp1").set("table", "tbl1");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("linemarker", "cycle");
    model.result("pg2").feature("tblp1").set("markerpos", "interp");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").run();
    model.result("pg2").set("xextra", 42.65);
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result().numerical().duplicate("gevs2", "gevs1");
    model.result().numerical("gevs2").setIndex("plistarr", "42.66[Hz]", 0);
    model.result().numerical("gevs2").setIndex("descr", "\u4f4d\u79fb\uff0cy \u5206\u91cf\uff0c\u6700\u5927 PSD", 0);
    model.result().numerical("gevs2").setIndex("expr", "rvib1.psd(Sb)", 1);
    model.result().numerical("gevs2").setIndex("unit", "(N/mm^2)^2/Hz", 1);
    model.result().numerical("gevs2").setIndex("descr", "\u5f2f\u66f2\u5e94\u529b\uff0c\u6700\u5927 PSD", 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97\u626b\u63cf 2");
    model.result().numerical("gevs2").set("table", "tbl2");
    model.result().numerical("gevs2").setResult();

    model.title("\u6df1\u6881\u7684\u968f\u673a\u632f\u52a8\u5206\u6790");

    model
         .description("\u8fd9\u4e2a\u9a8c\u8bc1\u793a\u4f8b\u7814\u7a76\u7b80\u652f\u6df1\u6881\u7684\u5f3a\u8feb\u968f\u673a\u632f\u52a8\uff0c\u901a\u8fc7\u5177\u6709\u5747\u5300\u529f\u7387\u8c31\u5bc6\u5ea6 (PSD) \u7684\u5206\u5e03\u529b\u5bf9\u6881\u65bd\u52a0\u8f7d\u8377\uff0c\u8ba1\u7b97\u4e86\u4f4d\u79fb\u548c\u5f2f\u66f2\u5e94\u529b\u54cd\u5e94\u7684\u8f93\u51fa PSD\uff0c\u5e76\u5c06\u8ba1\u7b97\u503c\u4e0e\u89e3\u6790\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("random_vibration_deep_beam.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
