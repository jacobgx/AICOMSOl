/*
 * ladder_frame.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:00 by COMSOL 6.3.0.290. */
public class ladder_frame {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Beams_and_Shells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("shell", "Shell", "geom1");

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
    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").insertFile("ladder_frame_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("rmd1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").physics("shell").feature("to1").set("d", "th2");
    model.component("comp1").physics("shell").create("to2", "ThicknessOffset", 2);
    model.component("comp1").physics("shell").feature("to2").selection().named("geom1_sel1");
    model.component("comp1").physics("shell").feature("to2").set("d", "th1");
    model.component("comp1").physics("shell").feature("to2").set("OffsetDefinition", "top");
    model.component("comp1").physics("shell").create("to3", "ThicknessOffset", 2);
    model.component("comp1").physics("shell").feature("to3").selection().set(33, 34, 47, 48, 291, 292, 306, 307);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").physics("shell").feature("to3").set("d", "th1");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("eigwhich", "lr");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shellshl", "Shell");
    model.result().dataset("dset1shellshl").set("data", "dset1");
    model.result().dataset("dset1shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset1shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset1shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset1shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset1shellshl").set("seplevels", false);
    model.result().dataset("dset1shellshl").set("resolution", 2);
    model.result().dataset("dset1shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset1shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (shell)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("data", "dset1shellshl");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
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
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (shell)");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").set("edgecolor", "cyan");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"if(abs(shell.z)==1,shell.z, NaN)"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("data", "dset1shellshl");
    model.result("pg2").feature("surf1").label("\u9876\u90e8\u548c\u5e95\u90e8");
    model.result("pg2").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (shell)");
    model.result("pg2").run();

    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").label("\u60ac\u6302\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("uni1", 75, 78, 86, 87, 88, 89, 90, 121, 122, 123, 124, 125, 130);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("extract2", "Extract");
    model.component("comp1").geom("geom1").feature("extract2").selection("input").named("sel2");
    model.component("comp1").geom("geom1").feature("extract2").set("inputhandling", "remainder");
    model.component("comp1").geom("geom1").run("extract2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u60ac\u6302\u652f\u67b6\uff08\u8fb9\uff09");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .add("extract2(1)", 22, 23, 24, 25, 26, 27, 40, 41, 42, 43, 44);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u957f\u6881\uff08\u8fb9\uff09");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .add("extract2(2)", 275, 277, 278, 280, 312, 314, 341, 342, 362, 364, 384);

    model.param().label("\u60ac\u6302\u5750\u6807");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("x_fwl", "500[mm]", "\u5de6\u524d\u8f6e\uff0cx \u5206\u91cf");
    model.param().set("y_fwl", "339[mm]", "\u5de6\u524d\u8f6e\uff0cy \u5206\u91cf");
    model.param().set("z_fwl", "-200[mm]", "\u5de6\u524d\u8f6e\uff0cz \u5206\u91cf");
    model.param().set("x_fwr", "-500[mm]", "\u53f3\u524d\u8f6e\uff0cx \u5206\u91cf");
    model.param().set("y_fwr", "339[mm]", "\u53f3\u524d\u8f6e\uff0cy \u5206\u91cf");
    model.param().set("z_fwr", "-200[mm]", "\u53f3\u524d\u8f6e\uff0cz \u5206\u91cf");
    model.param().set("x_rwl", "470[mm]", "\u5de6\u540e\u8f6e\uff0cx \u5206\u91cf");
    model.param().set("y_rwl", "3912[mm]", "\u5de6\u540e\u8f6e\uff0cy \u5206\u91cf");
    model.param().set("z_rwl", "-200[mm]", "\u5de6\u540e\u8f6e\uff0cz \u5206\u91cf");
    model.param().set("x_rwr", "-470[mm]", "\u53f3\u540e\u8f6e\uff0cx \u5206\u91cf");
    model.param().set("y_rwr", "3912[mm]", "\u53f3\u540e\u8f6e\uff0cy \u5206\u91cf");
    model.param().set("z_rwr", "-200[mm]", "\u53f3\u540e\u8f6e\uff0cz \u5206\u91cf");

    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm1").selection("ent").set("extract2(2)", 543, 544, 545, 546);
    model.component("comp1").geom("geom1").run("cm1");
    model.component("comp1").geom("geom1").create("cm2", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm2").selection("ent").set("extract2(2)", 547, 548, 549, 550);
    model.component("comp1").geom("geom1").run("cm2");
    model.component("comp1").geom("geom1").create("cm3", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm3").selection("ent").set("extract2(2)", 606, 644);
    model.component("comp1").geom("geom1").run("cm3");
    model.component("comp1").geom("geom1").create("cm4", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm4").selection("ent").set("extract2(2)", 699, 705, 734, 740);
    model.component("comp1").geom("geom1").run("cm4");
    model.component("comp1").geom("geom1").create("cm5", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm5").selection("ent").set("extract2(2)", 700, 706, 735, 741);
    model.component("comp1").geom("geom1").run("cm5");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"geom1.cm1.x", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "geom1.cm1.y", 1);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "geom1.cm1.z", 2);
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"x_fwl", "y_fwl", "z_fwl"});
    model.component("comp1").geom("geom1").feature().duplicate("ls2", "ls1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord1", "geom1.cm2.x", 0);
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord1", "geom1.cm2.y", 1);
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord1", "geom1.cm2.z", 2);
    model.component("comp1").geom("geom1").feature().duplicate("ls3", "ls2");
    model.component("comp1").geom("geom1").feature("ls3").setIndex("coord1", "geom1.cm3.x", 0);
    model.component("comp1").geom("geom1").feature("ls3").setIndex("coord1", "geom1.cm3.y", 1);
    model.component("comp1").geom("geom1").feature("ls3").setIndex("coord1", "geom1.cm3.z", 2);
    model.component("comp1").geom("geom1").feature().duplicate("ls4", "ls3");
    model.component("comp1").geom("geom1").feature("ls4").setIndex("coord1", "geom1.cm4.x", 0);
    model.component("comp1").geom("geom1").feature("ls4").setIndex("coord1", "geom1.cm4.y", 1);
    model.component("comp1").geom("geom1").feature("ls4").setIndex("coord1", "geom1.cm4.z", 2);
    model.component("comp1").geom("geom1").feature("ls4").set("coord2", new String[]{"x_rwl", "y_rwl", "z_rwl"});
    model.component("comp1").geom("geom1").feature().duplicate("ls5", "ls4");
    model.component("comp1").geom("geom1").feature("ls5").setIndex("coord1", "geom1.cm5.x", 0);
    model.component("comp1").geom("geom1").feature("ls5").setIndex("coord1", "geom1.cm5.y", 1);
    model.component("comp1").geom("geom1").feature("ls5").setIndex("coord1", "geom1.cm5.z", 2);
    model.component("comp1").geom("geom1").run("ls5");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("ls1", "ls2", "ls3", "ls4", "ls5");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u6841\u67b6");
    model.component("comp1").geom("geom1").feature("uni2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u4e0a\u90e8\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel5").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .add("extract2(2)", 21, 22, 25, 26, 64, 65, 211, 212, 239, 240, 256, 257, 856, 857, 864, 865, 898, 899, 1004, 1005, 1076, 1077, 1082, 1083);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u53d1\u52a8\u673a\u652f\u67b6");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel6").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection")
         .add("extract2(2)", 457, 458, 463, 464, 621, 622, 639, 640);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("shell").create("ee1", "EdgeToEdge", 1);
    model.component("comp1").physics("shell").feature("ee1").selection().named("geom1_sel3");
    model.component("comp1").physics("shell").feature("ee1").selection("edgDst").named("geom1_sel4");
    model.component("comp1").physics("shell").feature("ee1").set("ConnectedSurfaceDst", "Top");
    model.component("comp1").physics("shell").feature("ee1").set("weldEvaluation", true);
    model.component("comp1").physics("shell").feature("ee1").set("throatWeldSize", "3[mm]");
    model.component("comp1").physics("shell").feature("ee1").set("maxEqStress", "500[MPa]");
    model.component("comp1").physics("shell").feature("ee1").set("maxPerpendicularStress", "500[MPa]");
    model.component("comp1").physics("shell").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("shell").feature("el1").label("\u4e0a\u90e8\u548c\u6709\u6548\u8f7d\u8377");
    model.component("comp1").physics("shell").feature("el1").selection().named("geom1_sel5");
    model.component("comp1").physics("shell").feature("el1").set("forceType", "Resultant");
    model.component("comp1").physics("shell").feature("el1").set("F", new String[]{"0", "0", "-2000[kg]*g_const"});
    model.component("comp1").physics("shell").feature("el1").set("xaType", "Coordinates");
    model.component("comp1").physics("shell").feature("el1").set("x_a", new String[]{"0", "3[m]", "1[m]"});
    model.component("comp1").physics("shell").create("el2", "EdgeLoad", 1);
    model.component("comp1").physics("shell").feature("el2").label("\u53d1\u52a8\u673a");
    model.component("comp1").physics("shell").feature("el2").selection().named("geom1_sel6");
    model.component("comp1").physics("shell").feature("el2").set("forceType", "Resultant");
    model.component("comp1").physics("shell").feature("el2").set("F", new String[]{"0", "0", "-400[kg]*g_const"});
    model.component("comp1").physics("shell").feature("el2").set("xaType", "Coordinates");
    model.component("comp1").physics("shell").feature("el2").set("x_a", new String[]{"0", "0.4[m]", "0.1[m]"});
    model.component("comp1").physics("shell").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("shell").create("att1", "Attachment", 1);
    model.component("comp1").physics("shell").feature("att1").selection().set(606, 607, 608, 609);
    model.component("comp1").physics("shell").feature().duplicate("att2", "att1");
    model.component("comp1").physics("shell").feature("att2").selection().set(610, 611, 612, 613);
    model.component("comp1").physics("shell").feature().duplicate("att3", "att2");
    model.component("comp1").physics("shell").feature("att3").selection().set(697, 698);
    model.component("comp1").physics("shell").feature().duplicate("att4", "att3");
    model.component("comp1").physics("shell").feature("att4").selection().set(303, 304, 305, 306);
    model.component("comp1").physics("shell").feature().duplicate("att5", "att4");
    model.component("comp1").physics("shell").feature("att5").selection().set(307, 308, 309, 310);
    model.component("comp1").physics("shell").feature().duplicate("att6", "att5");
    model.component("comp1").physics("shell").feature("att6").selection().set(910, 911);
    model.component("comp1").physics("shell").feature().duplicate("att7", "att6");
    model.component("comp1").physics("shell").feature("att7").selection().set(795, 796, 836, 837);
    model.component("comp1").physics("shell").feature().duplicate("att8", "att7");
    model.component("comp1").physics("shell").feature("att8").selection().set(797, 798, 838, 839);
    model.component("comp1").physics("shell").feature().duplicate("att9", "att8");
    model.component("comp1").physics("shell").feature("att9").selection().set(87, 88, 124, 125);
    model.component("comp1").physics("shell").feature().duplicate("att10", "att9");
    model.component("comp1").physics("shell").feature("att10").selection().set(89, 90, 126, 127);
    model.component("comp1").physics().create("truss", "Truss", "geom1");

    model.study("std1").feature("eig").setSolveFor("/physics/truss", true);

    model.component("comp1").physics("truss").selection().named("geom1_csel1_edg");
    model.component("comp1").physics("truss").feature("emm1").set("E_mat", "userdef");
    model.component("comp1").physics("truss").feature("emm1").set("E", "1e15");
    model.component("comp1").physics("truss").feature("emm1").set("nu_mat", "userdef");
    model.component("comp1").physics("truss").feature("emm1").set("rho_mat", "userdef");
    model.component("comp1").physics("truss").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("truss").feature("disp1").selection().set(647);
    model.component("comp1").physics("truss").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("truss").feature("disp1").setIndex("U0", "shell.att1.u", 0);
    model.component("comp1").physics("truss").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("truss").feature("disp1").setIndex("U0", "shell.att1.v", 1);
    model.component("comp1").physics("truss").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("truss").feature("disp1").setIndex("U0", "shell.att1.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp2", "disp1");
    model.component("comp1").physics("truss").feature("disp2").selection().set(648);
    model.component("comp1").physics("truss").feature("disp2").setIndex("U0", "shell.att2.u", 0);
    model.component("comp1").physics("truss").feature("disp2").setIndex("U0", "shell.att2.v", 1);
    model.component("comp1").physics("truss").feature("disp2").setIndex("U0", "shell.att2.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp3", "disp2");
    model.component("comp1").physics("truss").feature("disp3").selection().set(649);
    model.component("comp1").physics("truss").feature("disp3").setIndex("U0", "shell.att3.u", 0);
    model.component("comp1").physics("truss").feature("disp3").setIndex("U0", "shell.att3.v", 1);
    model.component("comp1").physics("truss").feature("disp3").setIndex("U0", "shell.att3.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp4", "disp3");
    model.component("comp1").physics("truss").feature("disp4").selection().set(623);
    model.component("comp1").physics("truss").feature("disp4").setIndex("U0", "shell.att4.u", 0);
    model.component("comp1").physics("truss").feature("disp4").setIndex("U0", "shell.att4.v", 1);
    model.component("comp1").physics("truss").feature("disp4").setIndex("U0", "shell.att4.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp5", "disp4");
    model.component("comp1").physics("truss").feature("disp5").selection().set(624);
    model.component("comp1").physics("truss").feature("disp5").setIndex("U0", "shell.att5.u", 0);
    model.component("comp1").physics("truss").feature("disp5").setIndex("U0", "shell.att5.v", 1);
    model.component("comp1").physics("truss").feature("disp5").setIndex("U0", "shell.att5.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp6", "disp5");
    model.component("comp1").physics("truss").feature("disp6").selection().set(622);
    model.component("comp1").physics("truss").feature("disp6").setIndex("U0", "shell.att6.u", 0);
    model.component("comp1").physics("truss").feature("disp6").setIndex("U0", "shell.att6.v", 1);
    model.component("comp1").physics("truss").feature("disp6").setIndex("U0", "shell.att6.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp7", "disp6");
    model.component("comp1").physics("truss").feature("disp7").selection().set(651);
    model.component("comp1").physics("truss").feature("disp7").setIndex("U0", "shell.att7.u", 0);
    model.component("comp1").physics("truss").feature("disp7").setIndex("U0", "shell.att7.v", 1);
    model.component("comp1").physics("truss").feature("disp7").setIndex("U0", "shell.att7.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp8", "disp7");
    model.component("comp1").physics("truss").feature("disp8").selection().set(652);
    model.component("comp1").physics("truss").feature("disp8").setIndex("U0", "shell.att8.u", 0);
    model.component("comp1").physics("truss").feature("disp8").setIndex("U0", "shell.att8.v", 1);
    model.component("comp1").physics("truss").feature("disp8").setIndex("U0", "shell.att8.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp9", "disp8");
    model.component("comp1").physics("truss").feature("disp9").selection().set(619);
    model.component("comp1").physics("truss").feature("disp9").setIndex("U0", "shell.att9.u", 0);
    model.component("comp1").physics("truss").feature("disp9").setIndex("U0", "shell.att9.v", 1);
    model.component("comp1").physics("truss").feature("disp9").setIndex("U0", "shell.att9.w", 2);
    model.component("comp1").physics("truss").feature().duplicate("disp10", "disp9");
    model.component("comp1").physics("truss").feature("disp10").selection().set(620);
    model.component("comp1").physics("truss").feature("disp10").setIndex("U0", "shell.att10.u", 0);
    model.component("comp1").physics("truss").feature("disp10").setIndex("U0", "shell.att10.v", 1);
    model.component("comp1").physics("truss").feature("disp10").setIndex("U0", "shell.att10.w", 2);
    model.component("comp1").physics("truss").create("spdm1", "SpringDamperMaterial", 1);
    model.component("comp1").physics("truss").feature("spdm1").label("\u524d\u60ac\u67b6");
    model.component("comp1").physics("truss").feature("spdm1").selection().set(904, 945);
    model.component("comp1").physics("truss").feature("spdm1").set("k", "1e6[N/m]");
    model.component("comp1").physics("truss").create("spdm2", "SpringDamperMaterial", 1);
    model.component("comp1").physics("truss").feature("spdm2").label("\u540e\u60ac\u67b6");
    model.component("comp1").physics("truss").feature("spdm2").selection().set(907, 908, 946, 947);
    model.component("comp1").physics("truss").feature("spdm2").set("k", "3e6[N/m]");
    model.component("comp1").physics("truss").create("disp11", "Displacement1", 1);
    model.component("comp1").physics("truss").feature("disp11").selection().set(907, 908, 946, 947);
    model.component("comp1").physics("truss").feature("disp11").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("truss").create("disp12", "Displacement0", 0);
    model.component("comp1").physics("truss").feature("disp12").selection().set(618, 653);
    model.component("comp1").physics("truss").feature("disp12").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("truss").feature("disp12").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("truss").create("pin1", "Pinned", 0);
    model.component("comp1").physics("truss").feature("pin1").selection().set(621, 650);

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh("mesh2").automatic(false);
    model.component("comp1").mesh("mesh2").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").selection().named("geom1_sel2");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", "5e-3");
    model.component("comp1").mesh("mesh2").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size2").set("hmax", "1e-3");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature().duplicate("size3", "size2");
    model.component("comp1").mesh("mesh2").feature("ftri1").feature("size3").selection().named("geom1_sel4");
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std2").feature("stat").setSolveFor("/physics/truss", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset2shellshl", "Shell");
    model.result().dataset("dset2shellshl").set("data", "dset2");
    model.result().dataset("dset2shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset2shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset2shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset2shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset2shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset2shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset2shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset2shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset2shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset2shellshl").set("seplevels", false);
    model.result().dataset("dset2shellshl").set("resolution", 2);
    model.result().dataset("dset2shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset2shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2shellshl");
    model.result("pg3").label("\u5e94\u529b (shell)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"truss.misesGp"});
    model.result("pg4").feature("line1").set("threshold", "manual");
    model.result("pg4").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("line1").set("colortable", "Rainbow");
    model.result("pg4").feature("line1").set("colortabletrans", "none");
    model.result("pg4").feature("line1").set("colorscalemode", "linear");
    model.result("pg4").label("\u5e94\u529b (truss)");
    model.result("pg4").feature("line1").set("colortable", "Rainbow");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg4").feature("line1").set("resolution", "extrafine");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 1);
    model.result("pg4").feature("line1").set("tubeendcaps", false);
    model.result("pg4").feature("line1").create("def", "Deform");
    model.result("pg4").feature("line1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg4").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("unit", "MPa");
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormax", 180);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4f4d\u79fb (shell)");
    model.result("pg5").set("showlegends", true);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("data", "dset2shellshl");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg5").feature("surf1").create("def", "Deform");
    model.result("pg5").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg5").label("\u4f4d\u79fb (shell)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u8fb9\u8f7d\u8377 (shell)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg6").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").feature("surf1").set("inheritcolor", false);
    model.result("pg6").feature("surf1").set("inheritrange", false);
    model.result("pg6").feature("surf1").set("inherittransparency", false);
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def").set("scale", 0);
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326);
    model.result("pg6").feature("surf1").create("tran1", "Transparency");
    model.result("pg6").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg6").create("arwl1", "ArrowLine");
    model.result("pg6").feature("arwl1")
         .set("expr", new String[]{"shell.el1.flx", "shell.el1.fly", "shell.el1.flz"});
    model.result("pg6").feature("arwl1").set("placement", "gausspoints");
    model.result("pg6").feature("arwl1").set("arrowbase", "tail");
    model.result("pg6").feature("arwl1").label("\u4e0a\u90e8\u548c\u6709\u6548\u8f7d\u8377");
    model.result("pg6").feature("arwl1").set("inheritplot", "none");
    model.result("pg6").feature("arwl1").create("col", "Color");
    model.result("pg6").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arwl1").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arwl1").set("color", "green");
    model.result("pg6").feature("arwl1").create("def", "Deform");
    model.result("pg6").feature("arwl1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("arwl1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("arwl1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("arwl1").feature("def").set("scale", 0);
    model.result("pg6").feature().move("surf1", 1);
    model.result("pg6").create("arwl2", "ArrowLine");
    model.result("pg6").feature("arwl2")
         .set("expr", new String[]{"shell.el2.flx", "shell.el2.fly", "shell.el2.flz"});
    model.result("pg6").feature("arwl2").set("placement", "gausspoints");
    model.result("pg6").feature("arwl2").set("arrowbase", "tail");
    model.result("pg6").feature("arwl2").label("\u53d1\u52a8\u673a");
    model.result("pg6").feature("arwl2").set("inheritplot", "arwl1");
    model.result("pg6").feature("arwl2").create("col", "Color");
    model.result("pg6").feature("arwl2").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arwl2").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arwl2").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arwl2").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arwl2").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arwl2").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arwl2").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arwl2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arwl2").set("color", "green");
    model.result("pg6").feature("arwl2").create("def", "Deform");
    model.result("pg6").feature("arwl2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("arwl2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("arwl2").feature("def").set("scaleactive", true);
    model.result("pg6").feature("arwl2").feature("def").set("scale", 0);
    model.result("pg6").feature().move("surf1", 2);
    model.result("pg6").label("\u8fb9\u8f7d\u8377 (shell)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").label("Weld Failure Index (shell)");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "Weld Verification, Weld Failure Index");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"shell.ee1.weld_fi"});
    model.result("pg7").feature("line1").set("threshold", "manual");
    model.result("pg7").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("line1").set("colortable", "Traffic");
    model.result("pg7").feature("line1").set("colorscalemode", "linear");
    model.result("pg7").feature("line1").set("colortabletrans", "none");
    model.result("pg7").feature("line1").set("descractive", false);
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("radiusexpr", "shell.d/2");
    model.result("pg7").feature("line1").set("tuberadiusscale", 1);
    model.result("pg7").feature("line1").set("tubeendcaps", false);
    model.result("pg7").feature("line1").label("\u8fb9\u5230\u8fb9 1");
    model.result("pg7").feature("line1").set("inheritplot", "none");
    model.result("pg7").label("Weld Failure Index (shell)");
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").label("\u8fde\u63a5\u533a\u57df\u6307\u793a\u5668 (shell)");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u8fde\u63a5\u533a\u57df\u6307\u793a\u5668");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("line1", "Line");
    model.result("pg8").feature("line1").set("expr", new String[]{"shell.i_conn"});
    model.result("pg8").feature("line1").set("threshold", "manual");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg8").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("line1").set("colortable", "Rainbow");
    model.result("pg8").feature("line1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("line1").set("colorscalemode", "linear");
    model.result("pg8").feature("line1").set("linetype", "tube");
    model.result("pg8").feature("line1").set("tubeendcaps", false);
    model.result("pg8").feature("line1").set("radiusexpr", "shell.d/2");
    model.result("pg8").feature("line1").set("tuberadiusscale", 1);
    model.result("pg8").feature("line1").set("smooth", "none");
    model.result("pg8").feature("line1").set("resolution", "extrafine");
    model.result("pg8").feature("line1").set("coloring", "gradient");
    model.result("pg8").feature("line1").set("topcolor", "red");
    model.result("pg8").feature("line1").set("bottomcolor", "gray");
    model.result("pg8").feature("line1").set("colortabletype", "discrete");
    model.result("pg8").feature("line1").set("bandcount", 2);
    model.result("pg8").feature("line1").set("rangecoloractive", true);
    model.result("pg8").feature("line1").set("rangecolormin", 0);
    model.result("pg8").feature("line1").set("rangecolormax", 1);
    model.result("pg8").feature("line1").set("colorcalibration", -1.5);
    model.result("pg8").feature("line1").set("colorlegend", false);
    model.result("pg8").feature("line1").set("descractive", false);
    model.result("pg8").label("\u8fde\u63a5\u533a\u57df\u6307\u793a\u5668 (shell)");
    model.result("pg8").run();

    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig")
         .set("disabledphysics", new String[]{"shell/el1", "shell/el2", "shell/gacc1", "shell/att1", "shell/att2", "shell/att3", "shell/att4", "shell/att5", "shell/att6", "shell/att7", 
         "shell/att8", "shell/att9", "shell/att10"});
    model.study("std1").feature("eig").setSolveFor("/physics/truss", false);
    model.study("std1").feature("eig")
         .set("disabledphysics", new String[]{"shell/el1", "shell/el2", "shell/gacc1", "shell/att1", "shell/att2", "shell/att3", "shell/att4", "shell/att5", "shell/att6", "shell/att7", 
         "shell/att8", "shell/att9", "shell/att10", "truss"});

    model.result("pg3").run();

    model.title("\u68af\u67b6");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5bf9\u8f7b\u578b\u5361\u8f66\u68af\u67b6\u7ed3\u6784\u8fdb\u884c\u7279\u5f81\u9891\u7387\u548c\u9759\u6001\u5206\u6790\uff0c\u5c55\u793a\u4e86\u5982\u4f55\u5c06\u5b9e\u4f53\u51e0\u4f55\u8f6c\u6362\u4e3a\u58f3\u6a21\u578b\uff0c\u5982\u4f55\u901a\u8fc7\u6307\u5b9a\u76f8\u5e94\u7684\u5408\u6210\u91cf\u6765\u5b9e\u73b0\u5206\u5e03\u8f7d\u8377\uff0c\u4ee5\u53ca\u5982\u4f55\u9a8c\u8bc1\u7194\u5408\u7ebf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("ladder_frame.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
