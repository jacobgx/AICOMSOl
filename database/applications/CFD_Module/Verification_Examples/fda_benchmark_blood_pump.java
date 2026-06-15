/*
 * fda_benchmark_blood_pump.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class fda_benchmark_blood_pump {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.study().create("std1");

    model.param().set("TIME", "0[s]");
    model.param().set("rho_f", "1035[kg/m^3]");
    model.param().descr("rho_f", "\u8840\u6db2\u5bc6\u5ea6");
    model.param().set("mu_f", "0.0035[Pa*s]");
    model.param().descr("mu_f", "\u8840\u6db2\u9ecf\u5ea6");
    model.param().set("Qb", "6.0[L/min]");
    model.param().descr("Qb", "\u5165\u53e3\u6d41\u7387");
    model.param().set("rp", "3500[rpm]");
    model.param().descr("rp", "\u53f6\u8f6e\u8f6c\u901f");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").insertFile("fda_benchmark_blood_pump_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("cmf11");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u8840\u6db2");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").selection().set(2);
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "constantRevolutionsPerTime");
    model.component("comp1").common("rot1").set("revolutionsPerTime", "rp");

    model.component("comp1").physics("spf").label("\u6e4d\u6d41\uff0ck-\u03b5 - \u521d\u6b65");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "ZeroFixedWall");
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(7);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(54);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("FullyDevelopedFlowOption", "V0");
    model.component("comp1").physics("spf").feature("inl1").set("V0fdf", "Qb");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(55);

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.000004");
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature().remove("cr1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(40);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 2);
    model.component("comp1").mesh("mesh1").feature().move("ftri1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(70, 72);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("elemcount", 800);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").selection().set(69, 71);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").set("elemcount", 800);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "1e-3");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", "4e-4");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", "1e-3");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmin", "1e-7");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hgrad", 1.4);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection()
         .set(9, 10, 11, 12, 13, 28, 29, 30, 31, 32, 33);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hmax", "1e-3");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hmin", "4e-4");
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(3, 4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 60);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemcount", 70);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis4").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "split");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 8);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 1.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u7814\u7a76 1 - k-epsilon");
    model.study("std1").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u58c1\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(1, 2, 3, 4, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 22, 23, 28, 29, 30, 31, 32, 33, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51);

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").selection().set(54);
    model.component("comp1").probe("bnd1").label("\u5165\u53e3\u538b\u529b");
    model.component("comp1").probe("bnd1").set("probename", "p_in");
    model.component("comp1").probe("bnd1").set("expr", "spf2.pA");
    model.component("comp1").probe("bnd1").set("unit", "Pa");
    model.component("comp1").probe().create("bnd2", "Boundary");
    model.component("comp1").probe("bnd2").set("intsurface", true);
    model.component("comp1").probe("bnd2").label("\u51fa\u53e3\u538b\u529b");
    model.component("comp1").probe("bnd2").set("probename", "p_out");
    model.component("comp1").probe("bnd2").selection().set(55);
    model.component("comp1").probe("bnd2").set("expr", "spf2.pA");
    model.component("comp1").probe("bnd2").set("unit", "Pa");
    model.component("comp1").probe("bnd2").set("window", "window1");
    model.component("comp1").probe("bnd2").set("windowtitle", "\u63a2\u9488\u56fe\u201c1\u201d");
    model.component("comp1").probe().create("bnd3", "Boundary");
    model.component("comp1").probe("bnd3").set("intsurface", true);
    model.component("comp1").probe("bnd3").label("\u6700\u5927\u58c1\u526a\u5207\u5e94\u529b - \u58f3\u8fb9\u7f18");
    model.component("comp1").probe("bnd3").set("probename", "tau_housing_max");
    model.component("comp1").probe("bnd3").set("type", "maximum");
    model.component("comp1").probe("bnd3").selection().set(1);
    model.component("comp1").probe("bnd3").set("expr", "sqrt(spf2.K_stressx^2+spf2.K_stressy^2+spf2.K_stressz^2)");
    model.component("comp1").probe("bnd3").set("unit", "N/m^2");
    model.component("comp1").probe("bnd3").set("window", "window2");
    model.component("comp1").probe("bnd3").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.component("comp1").probe().create("bnd4", "Boundary");
    model.component("comp1").probe("bnd4").set("intsurface", true);
    model.component("comp1").probe("bnd4").label("\u6700\u5927\u58c1\u526a\u5207\u5e94\u529b - \u5706\u89d2");
    model.component("comp1").probe("bnd4").set("probename", "tau_fillet_max");
    model.component("comp1").probe("bnd4").set("type", "maximum");
    model.component("comp1").probe("bnd4").selection().set(40);
    model.component("comp1").probe("bnd4").set("expr", "spf2.K_stressx*nx+spf2.K_stressy*ny+spf2.K_stressz*nz");
    model.component("comp1").probe("bnd4").set("window", "window3");
    model.component("comp1").probe("bnd4").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.component("comp1").probe().create("bnd5", "Boundary");
    model.component("comp1").probe("bnd5").set("intsurface", true);
    model.component("comp1").probe("bnd5").label("\u8f74\u626d\u77e9");
    model.component("comp1").probe("bnd5").set("probename", "shaft_torque");
    model.component("comp1").probe("bnd5").set("type", "integral");
    model.component("comp1").probe("bnd5").selection().set(28, 29, 30, 31);
    model.component("comp1").probe("bnd5").set("expr", "x*spf2.T_tracy-y*spf2.T_tracx");
    model.component("comp1").probe("bnd5").set("unit", "nN*m");
    model.component("comp1").probe("bnd5").set("window", "window4");
    model.component("comp1").probe("bnd5").set("windowtitle", "\u63a2\u9488\u56fe\u201c4\u201d");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(21);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().set(55);

    model.component("comp1").physics("spf").create("nturb1", "NewTurbulenceModel", -1);
    model.component("comp1").physics("spf").feature("nturb1").set("TurbulenceModelInterface", "TurbulentFlowSST");
    model.component("comp1").physics().create("spf2", "TurbulentFlowSST", "geom1");
    model.component("comp1").physics("spf2").selection().geom("geom1", 3);
    model.component("comp1").physics("spf2").selection().set(1, 2, 3, 4, 5, 6);
    model.component("comp1").physics("spf2").selection().inherit(false);
    model.component("comp1").physics("spf2").selection().embedded(false);

    model.disableUpdates(true);

    model.component("comp1").physics("spf2").prop("ShapeProperty").set("order_fluid", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("ShapeProperty").set("frame", new String[][]{{"spatial"}});
    model.component("comp1").physics("spf2").prop("EquationForm").set("form", new String[][]{{"Automatic"}});
    model.component("comp1").physics("spf2").prop("EquationForm").set("showAllModelInputs", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("Compressibility", new String[][]{{"Incompressible"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("textCompressibilityMixtureModel", new String[][]{{"\u201c\u6df7\u5408\u7269\u6a21\u578b\u201d\u591a\u7269\u7406\u573a\u8026\u5408\u4ec5\u9650\u4e8e\u4e0d\u53ef\u538b\u7f29\u6d41\u52a8"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("EnablePorousMediaDomains", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("IncludeGravity", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("ViscoelasticFlow", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("BackCompState", new String[][]{{"3"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty").set("isBrinkman", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("Tref_src", new String[][]{{"userdef"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty")
         .set("Tref", new String[][]{{"293.15[K]"}});
    model.component("comp1").physics("spf2").prop("PhysicalModelProperty").set("pref", new String[][]{{"1[atm]"}});
    model.component("comp1").physics("spf2").prop("TurbulenceModelProperty")
         .set("editTurbulenceModelParameters", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("StreamlineDiffusionOldForm", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("StreamlineDiffusion", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("CrosswindDiffusion", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("RANSStreamlineDiffusion", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("RANSCrosswindDiffusion", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("CrosswindDiffusionOldFormNS", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("CrosswindDiffusionOldFormRANS", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("useDynamicSubgridTimescale", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("ConsistentStabilization")
         .set("limitTimeStepInStabilization", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("InconsistentStabilization")
         .set("IsotropicDiffusion", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("InconsistentStabilization")
         .set("RANSIsotropicDiffusion", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("AdvancedSettingProperty")
         .set("PseudoTimeSetting", new String[][]{{"Automatic"}});
    model.component("comp1").physics("spf2").prop("AdvancedSettingProperty")
         .set("CFLNumbExpr", new String[][]{{"Automatic"}});
    model.component("comp1").physics("spf2").prop("AdvancedSettingProperty")
         .set("UScale", new String[][]{{"1[m/s]"}});
    model.component("comp1").physics("spf2").prop("AdvancedSettingProperty")
         .set("lScaleFact", new String[][]{{"0.035"}});
    model.component("comp1").physics("spf2").prop("AdvancedSettingProperty").set("useBNS", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PropertyEnablerProp")
         .set("allowOutOfPlaneProp", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PropertyEnablerProp")
         .set("allowRadiationProp", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("PropertyEnablerProp")
         .set("allowStokesProp", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").prop("PropertyEnablerProp").set("allowTurbProp", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("PropertyEnablerProp")
         .set("allowMaxwellStefan", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").prop("StudyStep").set("StudyStep", new String[][]{{"std1/frrot"}});

    model.study("std1").feature("frrot").setSolveFor("/physics/spf2", false);
    model.study().create("std2");
    model.study("std2").create("wdi", "WallDistanceInitialization");
    model.study("std2").feature("wdi").setSolveFor("/physics/spf", false);
    model.study("std2").feature("wdi").setSolveFor("/physics/spf2", false);
    model.study("std2").feature("wdi").setSolveFor("/physics/spf2", true);
    model.study("std2").create("frrot", "FrozenRotor");
    model.study("std2").feature("frrot").setSolveFor("/physics/spf", false);
    model.study("std2").feature("frrot").setSolveFor("/physics/spf2", false);
    model.study("std2").feature("frrot").setSolveFor("/physics/spf2", true);
    model.study("std2").feature("wdi").set("usesol", true);
    model.study("std2").feature("wdi").set("initstudy", "std1");
    model.study("std2").feature("wdi").set("notstudy", "std1");
    model.study("std2").feature("wdi").set("notsolnum", "last");

    model.component("comp1").physics("spf2").feature().copy("wallbc2", "spf/wallbc2");
    model.component("comp1").physics("spf2").feature().copy("inl1", "spf/inl1");
    model.component("comp1").physics("spf2").feature().copy("out1", "spf/out1");
    model.component("comp1").physics("spf2").feature("fp1").set("MixingLengthLimit", new String[][]{{"Automatic"}});
    model.component("comp1").physics("spf2").feature("fp1").set("rho_mat", new String[][]{{"from_mat"}});
    model.component("comp1").physics("spf2").feature("fp1")
         .set("minput_temperature_src", new String[][]{{"fromCommonDef"}});
    model.component("comp1").physics("spf2").feature("fp1").set("editModelInputs", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("fp1")
         .set("Constitutiverelation", new String[][]{{"Newtonian"}});
    model.component("comp1").physics("spf2").feature("fp1").set("mu_mat", new String[][]{{"from_mat"}});
    model.component("comp1").physics("spf2").feature("fp1").set("streamlinens", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("fp1").set("crosswindns", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("fp1").set("StudyStep", new String[][]{{"std1/frrot"}});
    model.component("comp1").physics("spf2").feature("init1").set("u_init", new String[]{"u", "v", "w"});
    model.component("comp1").physics("spf2").feature("init1").set("p_init", "p");
    model.component("comp1").physics("spf2").feature("init1").set("k_init", "k");
    model.component("comp1").physics("spf2").feature("init1").set("om_init", "1/max(sqrt(eps),spf.muT)*spf.rho*k");
    model.component("comp1").physics("spf2").feature("wallbc1").set("BoundaryCondition", new String[][]{{"NoSlip"}});
    model.component("comp1").physics("spf2").feature("wallbc1").set("SlidingWall", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("wallbc1")
         .set("TranslationalVelocityOption", new String[][]{{"AutomaticFromFrame"}});
    model.component("comp1").physics("spf2").feature("wallbc1").set("constraintOptions", new String[][]{{"auto"}});
    model.component("comp1").physics("spf2").feature("wallbc1")
         .set("constraintType", new String[][]{{"unidirectionalConstraint"}});
    model.component("comp1").physics("spf2").feature("wallbc1")
         .set("constraintMethod", new String[][]{{"elemental"}});
    model.component("comp1").physics("spf2").feature("wallbc1").set("ApplyWallRoughness", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("wallbc1").set("StudyStep", new String[][]{{"std1/frrot"}});
    model.component("comp1").physics("spf2").feature("grav1").set("g", new String[][]{{"0"}, {"0"}, {"-g_const"}});
    model.component("comp1").physics("spf2").feature("grav1")
         .set("buoyancyInducedTurbulence", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("grav1").set("StudyStep", new String[][]{{"std1/frrot"}});
    model.component("comp1").physics("spf2").feature("dcont1")
         .set("constraintOptions", new String[][]{{"nitscheConstraints"}});
    model.component("comp1").physics("spf2").feature("dcont1").set("pairs", new String[][]{});
    model.component("comp1").physics("spf2").feature("dcont1").set("pairsList", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("dcont1").set("pairDisconnect", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("dcont1").set("StudyStep", new String[][]{{"std1/frrot"}});
    model.component("comp1").physics("spf2").feature("wallbc2").set("BoundaryCondition", new String[][]{{"NoSlip"}});
    model.component("comp1").physics("spf2").feature("wallbc2").set("SlidingWall", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("wallbc2")
         .set("TranslationalVelocityOption", new String[][]{{"ZeroFixedWall"}});
    model.component("comp1").physics("spf2").feature("wallbc2").set("constraintOptions", new String[][]{{"auto"}});
    model.component("comp1").physics("spf2").feature("wallbc2")
         .set("constraintType", new String[][]{{"unidirectionalConstraint"}});
    model.component("comp1").physics("spf2").feature("wallbc2")
         .set("constraintMethod", new String[][]{{"elemental"}});
    model.component("comp1").physics("spf2").feature("wallbc2").set("ApplyWallRoughness", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("wallbc2").set("StudyStep", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("out1").set("BoundaryCondition", new String[][]{{"Pressure"}});
    model.component("comp1").physics("spf2").feature("out1").set("p0", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("out1").set("NormalFlow", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("out1").set("SuppressBackflow", new String[][]{{"1"}});
    model.component("comp1").physics("spf2").feature("out1").set("PressureType", new String[][]{{"StaticPressure"}});
    model.component("comp1").physics("spf2").feature("out1").set("LaminarOutflowOption", new String[][]{{"Uav"}});
    model.component("comp1").physics("spf2").feature("out1").set("Uav", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("out1").set("ConstrainEndPointsToZero", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("out1")
         .set("FullyDevelopedFlowOption", new String[][]{{"p0av"}});
    model.component("comp1").physics("spf2").feature("out1").set("p0avfdf", new String[][]{{"0"}});
    model.component("comp1").physics("spf2").feature("out1")
         .set("constraintOptions", new String[][]{{"pointwiseConstraints"}});
    model.component("comp1").physics("spf2").feature("out1")
         .set("constraintType", new String[][]{{"unidirectionalConstraint"}});
    model.component("comp1").physics("spf2").feature("out1").set("constraintMethod", new String[][]{{"elemental"}});
    model.component("comp1").physics("spf2").feature("out1").set("StudyStep", new String[][]{{"0"}});

    model.disableUpdates(false);

    model.study("std2").label("\u7814\u7a76 2 - SST");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");
    model.component("comp1").probe("bnd2").genResult("none");
    model.component("comp1").probe("bnd3").genResult("none");
    model.component("comp1").probe("bnd4").genResult("none");
    model.component("comp1").probe("bnd5").genResult("none");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("frrot", "FrozenRotor");
    model.study("std3").feature("frrot").setSolveFor("/physics/spf", false);
    model.study("std3").label("\u7814\u7a76 3 - SST - Qb \u626b\u63cf");
    model.study("std3").feature("frrot").set("useinitsol", true);
    model.study("std3").feature("frrot").set("initmethod", "sol");
    model.study("std3").feature("frrot").set("initstudy", "std2");
    model.study("std3").feature("frrot").set("usesol", true);
    model.study("std3").feature("frrot").set("notstudy", "std2");
    model.study("std3").feature("frrot").set("notsolmethod", "sol");
    model.study("std3").feature("frrot").set("useparam", true);
    model.study("std3").feature("frrot").setIndex("pname", "TIME", 0);
    model.study("std3").feature("frrot").setIndex("plistarr", "", 0);
    model.study("std3").feature("frrot").setIndex("punit", "s", 0);
    model.study("std3").feature("frrot").setIndex("pname", "TIME", 0);
    model.study("std3").feature("frrot").setIndex("plistarr", "", 0);
    model.study("std3").feature("frrot").setIndex("punit", "s", 0);
    model.study("std3").feature("frrot").setIndex("pname", "Qb", 0);
    model.study("std3").feature("frrot").setIndex("plistarr", "7 4.5 2.5", 0);
    model.study("std3").feature("frrot").setIndex("punit", "L/min", 0);
    model.study("std3").showAutoSequences("all");
    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("bnd1").genResult("none");
    model.component("comp1").probe("bnd2").genResult("none");
    model.component("comp1").probe("bnd3").genResult("none");
    model.component("comp1").probe("bnd4").genResult("none");
    model.component("comp1").probe("bnd5").genResult("none");

    model.sol("sol4").runAll();

    model.result().dataset("dset5").set("geom", "geom1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u901f\u5ea6 (spf2)");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("slc1", "Slice");
    model.result("pg6").feature("slc1").label("\u5207\u9762");
    model.result("pg6").feature("slc1").set("showsolutionparams", "on");
    model.result("pg6").feature("slc1").set("expr", "spf2.U");
    model.result("pg6").feature("slc1").set("smooth", "internal");
    model.result("pg6").feature("slc1").set("showsolutionparams", "on");
    model.result("pg6").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset5");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(1, 2, 3, 4, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 22, 23, 28, 29, 30, 31, 32, 33, 40, 41, 42, 43, 44, 45, 46, 47, 49, 50, 52, 53);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u538b\u529b (spf2)");
    model.result("pg7").set("data", "surf1");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "p2");
    model.result("pg7").feature("surf1").set("colortable", "Dipole");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u58c1\u5206\u8fa8\u7387 (spf2)");
    model.result("pg8").set("data", "surf1");
    model.result("pg8").setIndex("looplevel", 3, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "spf2.Delta_wPlus");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg6").run();
    model.result().dataset("surf1").label("\u5916\u58c1\uff0c\u7814\u7a76 3 - SST - Qb \u626b\u63cf/\u89e3 4");
    model.result().dataset("surf1").selection().named("sel1");
    model.result().dataset().duplicate("surf2", "surf1");
    model.result().dataset("surf2").label("\u5916\u58c1\uff0c\u7814\u7a76 2 - SST/\u89e3 2");
    model.result().dataset("surf2").set("data", "dset2");
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").label("\u53f6\u7247\u901a\u9053\u5e73\u9762");
    model.result().dataset("cpl1").set("data", "dset2");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", 0.006562);
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").label("\u95f4\u9699\u4e0b\u5e73\u9762");
    model.result().dataset("cpl2").set("data", "dset2");
    model.result().dataset("cpl2").set("quickplane", "xy");
    model.result().dataset("cpl2").set("quickz", "0.0005");
    model.result().dataset().create("cpl3", "CutPlane");
    model.result().dataset("cpl3").label("zx \u51fa\u53e3\u5e73\u9762");
    model.result().dataset("cpl3").set("data", "dset2");
    model.result().dataset("cpl3").set("quickplane", "zx");
    model.result().dataset("cpl3").set("quicky", -0.027805);
    model.result().dataset().create("cpl4", "CutPlane");
    model.result().dataset("cpl4").label("\u95f4\u9699\u4e0a\u5e73\u9762");
    model.result().dataset("cpl4").set("data", "dset2");
    model.result().dataset("cpl4").set("quickplane", "xy");
    model.result().dataset("cpl4").set("quickz", 0.0085);
    model.result().dataset().create("cpl5", "CutPlane");
    model.result().dataset("cpl5").label("zx \u5165\u53e3\u5e73\u9762");
    model.result().dataset("cpl5").set("data", "dset2");
    model.result().dataset("cpl5").set("quickplane", "zx");
    model.result().dataset().create("cpl6", "CutPlane");
    model.result().dataset("cpl6").label("yz \u5165\u53e3\u5e73\u9762");
    model.result().dataset("cpl6").set("data", "dset2");
    model.result().dataset().create("cpl7", "CutPlane");
    model.result().dataset("cpl7").label("\u53f6\u7247\u4e0a\u5e73\u9762");
    model.result().dataset("cpl7").set("data", "dset2");
    model.result().dataset("cpl7").set("quickplane", "xy");
    model.result().dataset("cpl7").set("quickz", "8[mm]-1.2[mm]");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").label("\u4e8c\u7ef4\u5f84\u5411\u622a\u7ebf");
    model.result().dataset("cln1").set("data", "cpl7");
    model.result().dataset("cln1").setIndex("genpoints", "0.03*cos(45[deg])", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-0.03*cos(45[deg])", 1, 1);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").label("\u4e8c\u7ef4\u6269\u6563\u5668\u622a\u7ebf");
    model.result().dataset("cln2").set("data", "cpl7");
    model.result().dataset("cln2").setIndex("genpoints", 0.0367, 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", 0.0367, 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", -0.04, 1, 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("\u5bfc\u5165\u7684\u5b9e\u9a8c\u7ed3\u679c - \u5f84\u5411\u622a\u7ebf 1");
    model.result().table("tbl2").importData("fda_benchmark_blood_pump_uxy_1_lab1.txt");
    model.result().table("tbl2").setIndex("headers", "r [m]", 0, 1);
    model.result().table("tbl2").setIndex("headers", "|U_xy| [m/s]", 1, 1);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u5bfc\u5165\u7684\u5b9e\u9a8c\u7ed3\u679c - \u5f84\u5411\u622a\u7ebf 2");
    model.result().table("tbl3").importData("fda_benchmark_blood_pump_uxy_1_lab2.txt");
    model.result().table("tbl3").setIndex("headers", "r [m]", 0, 1);
    model.result().table("tbl3").setIndex("headers", "|U_xy| [m/s]", 1, 1);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").label("\u5bfc\u5165\u7684\u5b9e\u9a8c\u7ed3\u679c - \u5f84\u5411\u622a\u7ebf 3");
    model.result().table("tbl4").importData("fda_benchmark_blood_pump_uxy_1_lab3a.txt");
    model.result().table("tbl4").setIndex("headers", "r [m]", 0, 1);
    model.result().table("tbl4").setIndex("headers", "|U_xy| [m/s]", 1, 1);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5")
         .label("\u5bfc\u5165\u7684\u5b9e\u9a8c\u7ed3\u679c - \u6269\u6563\u5668\u622a\u7ebf 1");
    model.result().table("tbl5").importData("fda_benchmark_blood_pump_uxy_2_lab1.txt");
    model.result().table("tbl5").setIndex("headers", "d [m]", 0, 1);
    model.result().table("tbl5").setIndex("headers", "|U_xy| [m/s]", 1, 1);
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6")
         .label("\u5bfc\u5165\u7684\u5b9e\u9a8c\u7ed3\u679c - \u6269\u6563\u5668\u622a\u7ebf 2");
    model.result().table("tbl6").importData("fda_benchmark_blood_pump_uxy_2_lab2.txt");
    model.result().table("tbl6").setIndex("headers", "d [m]", 0, 1);
    model.result().table("tbl6").setIndex("headers", "|U_xy| [m/s]", 1, 1);
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7")
         .label("\u5bfc\u5165\u7684\u5b9e\u9a8c\u7ed3\u679c - \u6269\u6563\u5668\u622a\u7ebf 3");
    model.result().table("tbl7").importData("fda_benchmark_blood_pump_uxy_2_lab3a.txt");
    model.result().table("tbl7").setIndex("headers", "d [m]", 0, 1);
    model.result().table("tbl7").setIndex("headers", "|U_xy| [m/s]", 1, 1);
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8").label("\u5bfc\u5165\u7684 - \u5b9e\u9a8c\u7ed3\u679c - \u538b\u529b\u6c34\u5934");
    model.result().table("tbl8").importData("fda_benchmark_blood_pump_pressure_head_exp.txt");
    model.result().table("tbl8").setIndex("headers", "\u6d41\u7387 (L/min)", 0, 1);
    model.result().table("tbl8").setIndex("headers", "\u538b\u529b\u6c34\u5934 (mmHg)", 1, 1);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u538b\u529b\u6c34\u5934 - \u7814\u7a76 3");
    model.result().numerical("gev1").set("data", "dset5");
    model.result().numerical("gev1").setIndex("expr", "abs(aveop1(p2)-aveop2(p2))", 0);
    model.result().numerical("gev1").setIndex("unit", "mmHg", 0);
    model.result().table().create("tbl9", "Table");
    model.result().table("tbl9").comments("\u538b\u529b\u6c34\u5934 - \u7814\u7a76 3");
    model.result().numerical("gev1").set("table", "tbl9");
    model.result().numerical("gev1").setResult();
    model.result().table("tbl9").label("\u538b\u529b\u6c34\u5934 - \u7814\u7a76 3");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u538b\u529b\u6c34\u5934 - \u7814\u7a76 2");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("expr", "Qb", 0);
    model.result().numerical("gev2").setIndex("unit", "L/min", 0);
    model.result().numerical("gev2").setIndex("expr", "abs(aveop1(p2)-aveop2(p2))", 1);
    model.result().numerical("gev2").setIndex("unit", "mmHg", 1);
    model.result().table().create("tbl10", "Table");
    model.result().table("tbl10").comments("\u538b\u529b\u6c34\u5934 - \u7814\u7a76 2");
    model.result().numerical("gev2").set("table", "tbl10");
    model.result().numerical("gev2").setResult();
    model.result().table("tbl10").label("\u538b\u529b\u6c34\u5934 - \u7814\u7a76 2");
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u8868\u9762\u79ef\u5206 1 - \u6d41\u5165");
    model.result().numerical("int1").selection().set(52);
    model.result().numerical("int1").set("data", "dset2");
    model.result().numerical("int1").setIndex("expr", "spf2.rho*(u2*nx+v2*ny+w2*nz)", 0);
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").label("\u8868\u9762\u79ef\u5206 1 - \u6d41\u51fa");
    model.result().numerical("int2").selection().set(53);
    model.result().numerical("int2").set("data", "dset2");
    model.result().numerical("int2").setIndex("expr", "spf2.rho*(u2*nx+v2*ny+w2*nz)", 0);
    model.result("pg1").set("window", "window5");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c5\u201d");
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7edd\u5bf9\u538b\u529b (Pa)");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5165\u53e3\u538b\u529b");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7edd\u5bf9\u538b\u529b (Pa)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u51fa\u53e3\u538b\u529b");
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6700\u5927\u58c1\u526a\u5207\u5e94\u529b (N/m^2)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u58f3\u8fb9\u7f18\u4e0a\u7684\u6700\u5927\u58c1\u526a\u5207\u5e94\u529b");
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6700\u5927\u58c1\u526a\u5207\u5e94\u529b");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u5706\u89d2\u4e0a\u7684\u6700\u5927\u58c1\u526a\u5207\u5e94\u529b");
    model.result("pg4").set("window", "window3");
    model.result("pg4").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg4").run();
    model.result("pg5").set("window", "window4");
    model.result("pg5").set("windowtitle", "\u63a2\u9488\u56fe\u201c4\u201d");
    model.result("pg5").run();
    model.result("pg5").set("showlegends", false);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u8f74\u626d\u77e9 (nN*m)");

    model.component("comp1").view().create("view8", "geom1");
    model.component("comp1").view("view8").set("showgrid", false);
    model.component("comp1").view("view8").hideObjects().create("hide1");
    model.component("comp1").view("view8").hideObjects("hide1").init(2);
    model.component("comp1").view("view8").hideObjects("hide1")
         .set("cmf11", 4, 5, 6, 8, 14, 16, 18, 20, 21, 22, 24, 25, 26, 27, 34, 35, 36, 37, 39, 42, 45, 47, 48, 50, 51, 52, 54, 55);

    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("view", "view8");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").run();
    model.result("pg6").feature("slc1").active(false);
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "cpl1");
    model.result("pg6").feature("surf1").set("expr", "spf2.U");
    model.result("pg6").feature("surf1").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("data", "cpl3");
    model.result("pg6").feature("surf2").set("expr", "spf2.U");
    model.result("pg6").feature("surf2").set("colorlegend", false);
    model.result("pg6").feature("surf2").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("surf3", "Surface");
    model.result("pg6").feature("surf3").set("data", "cpl5");
    model.result("pg6").feature("surf3").set("expr", "spf2.U");
    model.result("pg6").feature("surf3").set("colorlegend", false);
    model.result("pg6").feature("surf3").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").create("surf4", "Surface");
    model.result("pg6").feature("surf4").set("data", "dset2");
    model.result("pg6").feature("surf4").set("coloring", "uniform");
    model.result("pg6").feature("surf4").set("color", "gray");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").set("data", "surf2");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("data", "cpl1");
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").set("data", "cpl3");
    model.result("pg7").feature("surf2").set("expr", "p2");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u6e4d\u6d41\u9ecf\u5ea6");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("data", "cpl1");
    model.result("pg9").feature("surf1").set("expr", "spf2.muT");
    model.result("pg9").run();
    model.result("pg9").create("surf2", "Surface");
    model.result("pg9").feature("surf2").set("data", "cpl3");
    model.result("pg9").feature("surf2").set("expr", "spf2.muT");
    model.result("pg9").feature("surf2").set("colorlegend", false);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("xlabel", "r [m]");
    model.result("pg10").set("ylabel", "|U_xy| [m/s]");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10")
         .set("title", "\u6cbf\u5f84\u5411\u622a\u7ebf\u7684\u901f\u5ea6\u5927\u5c0f\u4e0e\u5b9e\u9a8c\u7ed3\u679c\u7684\u6bd4\u8f83\u3002");
    model.result("pg10").label("Uxy \u7684\u5927\u5c0f - \u5f84\u5411");
    model.result("pg10").set("data", "cln1");
    model.result("pg10").set("axislimits", true);
    model.result("pg10").set("xmin", 0);
    model.result("pg10").set("xmax", 0.027);
    model.result("pg10").set("ymin", 0);
    model.result("pg10").set("ymax", 9);
    model.result("pg10").set("legendpos", "upperleft");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").set("expr", "sqrt(u2^2+v2^2)");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "sqrt(x^2+y^2)");
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").feature("lngr1").set("legendmethod", "manual");
    model.result("pg10").feature("lngr1").setIndex("legends", "COMSOL", 0);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("tblp1", "Table");
    model.result("pg10").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg10").feature("tblp1").set("linewidth", "preference");
    model.result("pg10").feature("tblp1").set("table", "tbl2");
    model.result("pg10").feature("tblp1").set("xaxisdata", 1);
    model.result("pg10").feature("tblp1").set("linestyle", "none");
    model.result("pg10").feature("tblp1").set("linecolor", "blue");
    model.result("pg10").feature("tblp1").set("linemarker", "diamond");
    model.result("pg10").feature("tblp1").set("legend", true);
    model.result("pg10").feature("tblp1").set("legendmethod", "manual");
    model.result("pg10").feature("tblp1").setIndex("legends", "\u5b9e\u9a8c\u5ba4 - 1 \u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("tblp2", "Table");
    model.result("pg10").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg10").feature("tblp2").set("linewidth", "preference");
    model.result("pg10").feature("tblp2").set("table", "tbl3");
    model.result("pg10").feature("tblp2").set("xaxisdata", 1);
    model.result("pg10").feature("tblp2").set("linestyle", "none");
    model.result("pg10").feature("tblp2").set("linecolor", "red");
    model.result("pg10").feature("tblp2").set("linemarker", "square");
    model.result("pg10").feature("tblp2").set("legend", true);
    model.result("pg10").feature("tblp2").set("legendmethod", "manual");
    model.result("pg10").feature("tblp2").setIndex("legends", "\u5b9e\u9a8c\u5ba4 - 2 \u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("tblp3", "Table");
    model.result("pg10").feature("tblp3").set("markerpos", "datapoints");
    model.result("pg10").feature("tblp3").set("linewidth", "preference");
    model.result("pg10").feature("tblp3").set("table", "tbl4");
    model.result("pg10").feature("tblp3").set("xaxisdata", 1);
    model.result("pg10").feature("tblp3").set("linestyle", "none");
    model.result("pg10").feature("tblp3").set("linecolor", "black");
    model.result("pg10").feature("tblp3").set("linemarker", "triangle");
    model.result("pg10").feature("tblp3").set("legend", true);
    model.result("pg10").feature("tblp3").set("legendmethod", "manual");
    model.result("pg10").feature("tblp3").setIndex("legends", "\u5b9e\u9a8c\u5ba4 - 3 \u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("Uxy \u7684\u5927\u5c0f - \u6269\u6563\u5668");
    model.result("pg11").set("data", "cln2");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("xlabel", "d [m]");
    model.result("pg11").set("ylabel", "|U_xy|");
    model.result("pg11").set("titletype", "manual");
    model.result("pg11")
         .set("title", "\u6cbf\u6269\u6563\u5668\u622a\u7ebf\u7684\u901f\u5ea6\u5927\u5c0f\u4e0e\u5b9e\u9a8c\u7ed3\u679c\u7684\u6bd4\u8f83\u3002");
    model.result("pg11").set("axislimits", true);
    model.result("pg11").set("xmin", 0);
    model.result("pg11").set("xmax", 0.01);
    model.result("pg11").set("ymin", 0);
    model.result("pg11").set("ymax", 7);
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg11").feature("lngr1").set("linewidth", "preference");
    model.result("pg11").feature("lngr1").set("expr", "sqrt(u2^2+v2^2)");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "manual");
    model.result("pg11").feature("lngr1").setIndex("legends", "COMSOL", 0);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").create("tblp1", "Table");
    model.result("pg11").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg11").feature("tblp1").set("linewidth", "preference");
    model.result("pg11").feature("tblp1").set("table", "tbl5");
    model.result("pg11").feature("tblp1").set("xaxisdata", 1);
    model.result("pg11").feature("tblp1").set("linestyle", "none");
    model.result("pg11").feature("tblp1").set("linecolor", "blue");
    model.result("pg11").feature("tblp1").set("linemarker", "diamond");
    model.result("pg11").feature("tblp1").set("legend", true);
    model.result("pg11").feature("tblp1").set("legendmethod", "manual");
    model.result("pg11").feature("tblp1").setIndex("legends", "\u5b9e\u9a8c\u5ba4 - 1 \u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").create("tblp2", "Table");
    model.result("pg11").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg11").feature("tblp2").set("linewidth", "preference");
    model.result("pg11").feature("tblp2").set("table", "tbl6");
    model.result("pg11").feature("tblp2").set("xaxisdata", 1);
    model.result("pg11").feature("tblp2").set("linestyle", "none");
    model.result("pg11").feature("tblp2").set("linecolor", "red");
    model.result("pg11").feature("tblp2").set("linemarker", "square");
    model.result("pg11").feature("tblp2").set("legend", true);
    model.result("pg11").feature("tblp2").set("legendmethod", "manual");
    model.result("pg11").feature("tblp2").setIndex("legends", "\u5b9e\u9a8c\u5ba4 - 2 \u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").create("tblp3", "Table");
    model.result("pg11").feature("tblp3").set("markerpos", "datapoints");
    model.result("pg11").feature("tblp3").set("linewidth", "preference");
    model.result("pg11").feature("tblp3").set("table", "tbl7");
    model.result("pg11").feature("tblp3").set("xaxisdata", 1);
    model.result("pg11").feature("tblp3").set("linestyle", "none");
    model.result("pg11").feature("tblp3").set("linecolor", "black");
    model.result("pg11").feature("tblp3").set("linemarker", "triangle");
    model.result("pg11").feature("tblp3").set("legend", true);
    model.result("pg11").feature("tblp3").set("legendmethod", "manual");
    model.result("pg11").feature("tblp3").setIndex("legends", "\u5b9e\u9a8c\u5ba4 - 2 \u5b9e\u9a8c\u7ed3\u679c", 0);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u538b\u529b\u6c34\u5934 - \u5b9e\u9a8c\u7ed3\u679c\u6bd4\u8f83");
    model.result("pg12").set("titletype", "manual");
    model.result("pg12")
         .set("title", "\u79bb\u5fc3\u6cf5\u5728\u4e0d\u540c\u5165\u53e3\u6d41\u7387\u4e0b\u7684\u538b\u529b\u6c34\u5934\u3002");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u6d41\u7387 (L/min)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u538b\u529b\u6c34\u5934 (mmHg)");
    model.result("pg12").create("tblp1", "Table");
    model.result("pg12").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg12").feature("tblp1").set("linewidth", "preference");
    model.result("pg12").feature("tblp1").set("table", "tbl8");
    model.result("pg12").feature("tblp1").set("xaxisdata", 1);
    model.result("pg12").feature("tblp1").set("linestyle", "none");
    model.result("pg12").feature("tblp1").set("linecolor", "black");
    model.result("pg12").feature("tblp1").set("linewidth", 6);
    model.result("pg12").feature("tblp1").set("linemarker", "circle");
    model.result("pg12").feature("tblp1").set("legendmethod", "manual");
    model.result("pg12").feature("tblp1").set("legend", true);
    model.result("pg12").feature("tblp1")
         .setIndex("legends", "\u5b9e\u9a8c\uff08Malinauskas \u7b49\u4eba\uff0cASAIO Journal 2017\uff09", 0);
    model.result("pg12").run();
    model.result("pg12").create("tblp2", "Table");
    model.result("pg12").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg12").feature("tblp2").set("linewidth", "preference");
    model.result("pg12").feature("tblp2").set("table", "tbl9");
    model.result("pg12").feature("tblp2").set("linestyle", "none");
    model.result("pg12").feature("tblp2").set("linecolor", "blue");
    model.result("pg12").feature("tblp2").set("linewidth", 6);
    model.result("pg12").feature("tblp2").set("linemarker", "diamond");
    model.result("pg12").feature("tblp2").set("legend", true);
    model.result("pg12").feature("tblp2").set("legendmethod", "manual");
    model.result("pg12").feature("tblp2").setIndex("legends", "COMSOL", 0);
    model.result("pg12").run();
    model.result("pg12").create("tblp3", "Table");
    model.result("pg12").feature("tblp3").set("markerpos", "datapoints");
    model.result("pg12").feature("tblp3").set("linewidth", "preference");
    model.result("pg12").feature("tblp3").set("table", "tbl10");
    model.result("pg12").feature("tblp3").set("xaxisdata", 1);
    model.result("pg12").feature("tblp3").set("linecolor", "blue");
    model.result("pg12").feature("tblp3").set("linewidth", 6);
    model.result("pg12").feature("tblp3").set("linemarker", "diamond");
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").set("data", "cpl1");
    model.result("pg13").set("titletype", "manual");
    model.result("pg13")
         .set("title", "\u8fd0\u884c\u6761\u4ef6\u4e3a 3500 [rpm] \u548c\u5165\u53e3\u6d41\u7387\u4e3a 6 [L/min] \u65f6\uff0c\u53f6\u7247\u901a\u9053\u5e73\u9762\u7684\u901f\u5ea6\u5927\u5c0f\u3002");
    model.result("pg13").label("\u53f6\u7247\u901a\u9053\u5e73\u9762\u7684\u901f\u5ea6\u5927\u5c0f");
    model.result("pg13").set("title", "\u53f6\u7247\u901a\u9053\u5e73\u9762\u7684\u901f\u5ea6\u5927\u5c0f");
    model.result("pg13").set("paramindicator", "Qb(2)=6 L/min");
    model.result("pg13").set("edges", false);
    model.result("pg13").set("view", "new");
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "spf2.U");
    model.result("pg13").feature("surf1").create("filt1", "Filter");
    model.result("pg13").run();
    model.result("pg13").feature("surf1").feature("filt1").set("expr", "x>-0.005  && x<0.04 && y<0.005");
    model.result("pg13").run();

    model.title("FDA \u57fa\u51c6\u8840\u6cf5");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u8ba1\u7b97\u79bb\u5fc3\u5f0f\u8840\u6cf5\u4e2d\u7684\u6e4d\u6d41\u573a\uff0c\u5f97\u5230\u7684\u58c1\u526a\u5207\u5e94\u529b\u53ef\u4ee5\u7528\u6765\u9884\u6d4b\u5728\u5404\u79cd\u8f7d\u8377\u6761\u4ef6\u4e0b\u901a\u8fc7\u6cf5\u7684\u8840\u6db2\u53ef\u80fd\u53d1\u751f\u7684\u635f\u4f24\u3002\u6a21\u578b\u4e2d\u7684\u51e0\u4f55\u53d6\u81ea FDA \u7684 Computational Round Robin\uff0c\u5e76\u5728\u7ed9\u5b9a\u7684\u6d41\u7387\u548c\u6cf5\u901f\u4e0b\u8fdb\u884c\u591a\u6b21\u4eff\u771f\u3002\u901f\u5ea6\u5927\u5c0f\u548c\u538b\u529b\u5934\u7684\u4e8c\u7ef4\u5256\u9762\u4e0e\u5b9e\u9a8c\u6570\u636e\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("fda_benchmark_blood_pump.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
