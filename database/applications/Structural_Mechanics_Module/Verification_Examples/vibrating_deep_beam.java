/*
 * vibrating_deep_beam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:27 by COMSOL 6.3.0.290. */
public class vibrating_deep_beam {

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
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/beam", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.param().set("L", "10[m]");
    model.param().descr("L", "\u6881\u957f");
    model.param().set("F0", "1e6[N/m]");
    model.param().descr("F0", "\u8fb9\u8f7d\u8377");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").run("fin");

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
    model.component("comp1").mesh("mesh1").feature("edg1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u81ea\u7531\u632f\u52a8");
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 10);
    model.study("std1").feature("eig").set("shift", "40");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("rtol", 1.0E-15);
    model.sol("sol1").run("e1");

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
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u81ea\u7531\u632f\u52a8)");
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
    model.result().evaluationGroup("std1mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u81ea\u7531\u632f\u52a8)");
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
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("tuberadiusscale", 0.1);
    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().dataset("dset1beam")
         .set("defaultPlotIDs", new String[]{"modalStress3D|beam", "geometryOrientation|beam"});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1beam");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").label("\u51e0\u4f55\u548c\u65b9\u5411 (beam)");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"mod(beam.csIndex*3, 10)"});
    model.result("pg2").feature("surf1").set("coloring", "colortable");
    model.result("pg2").feature("surf1").set("colortable", "Cyclic");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 10);
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg2").feature("surf1").set("rangecolormax", 9.5);
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.5);
    model.result("pg2").feature("surf1").feature("tran1").active(false);
    model.result("pg2").create("arws1", "ArrowLine");
    model.result("pg2").feature("arws1").set("data", "dset1");
    model.result("pg2").feature("arws1").set("solutionparams", "parent");
    model.result("pg2").feature("arws1").label("\u5c40\u90e8 Y \u65b9\u5411\uff08\u7eff\u8272\uff09");
    model.result("pg2").feature("arws1")
         .set("expr", new String[]{"beam.beamsys.e_y1", "beam.beamsys.e_y2", "beam.beamsys.e_y3"});
    model.result("pg2").feature("arws1").set("color", "green");
    model.result("pg2").feature("arws1").set("placement", "gausspoints");
    model.result("pg2").feature("arws1").active(false);
    model.result("pg2").create("arws2", "ArrowLine");
    model.result("pg2").feature("arws2").set("data", "dset1");
    model.result("pg2").feature("arws2").set("solutionparams", "parent");
    model.result("pg2").feature("arws2").label("\u5c40\u90e8 Z \u65b9\u5411\uff08\u84dd\u8272\uff09");
    model.result("pg2").feature("arws2")
         .set("expr", new String[]{"beam.beamsys.e_z1", "beam.beamsys.e_z2", "beam.beamsys.e_z3"});
    model.result("pg2").feature("arws2").set("color", "blue");
    model.result("pg2").feature("arws2").set("placement", "gausspoints");
    model.result("pg2").feature("arws2").set("inheritplot", "arws1");
    model.result("pg2").feature("arws2").set("inheritcolor", false);
    model.result("pg2").feature("arws2").set("inheritrange", false);
    model.result("pg2").feature("arws2").active(false);
    model.result("pg2").label("\u51e0\u4f55\u548c\u65b9\u5411 (beam)");
    model.result("pg2").run();

    model.component("comp1").physics("beam").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("beam").feature("el1").selection().all();
    model.component("comp1").physics("beam").feature("el1")
         .set("forceReferenceLength", new String[]{"0", "0", "F0"});
    model.component("comp1").physics("beam").feature("el1").set("harmonicPerturbation", true);
    model.component("comp1").physics("beam").feature("emm1").create("dmp1", "Damping", 1);
    model.component("comp1").physics("beam").feature("emm1").feature("dmp1").set("alpha_dM", 5.36);
    model.component("comp1").physics("beam").feature("emm1").feature("dmp1").set("beta_dK", "7.46e-5");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1")
         .setTableData(new double[][]{{4.26612044566661, 0.1009818183635054}, {5.0340221258866, 0.08591029468232829}, {5.80192380610659, 0.07487593045716132}, {6.56982548632658, 0.06646309834628668}, {7.3377271665465695, 0.059848759197542796}, {8.105628846766558, 0.05452176237958726}, {8.873530526986547, 0.05014789401808079}, {9.641432207206536, 0.046499415455287754}, {10.409333887426525, 0.04341578960460765}, {11.177235567646514, 0.040780596441406504}, {11.945137247866503, 0.03850735251881339}, {12.713038928086492, 0.03653046971940268}, {13.480940608306481, 0.03479930405376979}, {14.24884228852647, 0.033274128799687164}, {15.016743968746459, 0.03192334315538289}, {15.784645648966448, 0.030721495664294714}, {16.55254732918644, 0.029647857820562673}, {17.320449009406428, 0.028685377109513785}, {18.088350689626417, 0.02781989672651387}, {18.856252369846406, 0.027039565952751518}, {19.624154050066394, 0.02633438896453362}, {20.392055730286383, 0.025695875585250556}, {21.159957410506372, 0.025116768083284412}, {21.92785909072636, 0.02459082537424782}, {22.69576077094635, 0.024112651031790777}, {23.46366245116634, 0.023677555070830934}, {24.231564131386328, 0.02328144201143722}, {24.999465811606317, 0.022920719572577936}, {25.767367491826306, 0.022592223692152323}, {26.535269172046295, 0.022293156566052002}, {27.303170852266284, 0.022021035143130765}, {28.071072532486273, 0.0217736480738876}, {28.838974212706262, 0.021549019537170706}, {29.60687589292625, 0.02134537869615527}, {30.37477757314624, 0.021161133787403263}, {31.14267925336623, 0.020994850043322594}, {31.910580933586218, 0.020845230802292607}, {32.67848261380621, 0.020711101282113904}, {33.4463842940262, 0.020591394588748257}, {34.214285974246195, 0.02048513960916836}, {34.98218765446619, 0.020391450498807834}, {35.75008933468618, 0.020309517523850625}, {36.51799101490617, 0.020238599058932515}, {37.285892695126165, 0.020178014573684944}, {38.05379437534616, 0.02012713846844177}, {38.82169605556615, 0.020085394641532396}, {39.58959773578614, 0.02005225168882964}, {40.357499416006135, 0.020027218651340738}, {41.12540109622613, 0.020009841239209313}, {41.89330277644612, 0.01999969847100022}, {42.66120445666611, 0.01999639967594167}, {50.34022125886601, 0.02027092651895036}, {58.019238061065906, 0.020949169307560064}, {65.6982548632658, 0.021889565307599005}, {73.3772716654657, 0.023009810603851016}, {81.0562884676656, 0.024258790133181867}, {88.73530526986549, 0.025603082508157623}, {96.41432207206539, 0.027019913863004723}, {104.09333887426529, 0.028493230489063114}, {111.77235567646518, 0.0300113903838694}, {119.45137247866508, 0.031565745202736493}, {127.13038928086497, 0.033149736133921824}, {134.80940608306489, 0.034758298778484936}, {142.48842288526478, 0.03638746046420308}, {150.16743968746468, 0.03803406111089905}, {157.84645648966458, 0.03969555557291664}, {165.52547329186447, 0.041369870999669836}, {173.20449009406437, 0.04305530213969136}, {180.88350689626427, 0.04475043331251777}, {188.56252369846416, 0.04645407944626793}, {196.24154050066406, 0.04816524095857254}, {203.92055730286395, 0.04988306883177064}, {211.59957410506385, 0.05160683729270042}, {219.27859090726375, 0.05333592223292317}, {226.95760770946364, 0.05506978400980387}, {234.63662451166354, 0.05680795362483429}, {242.31564131386344, 0.05855002153002131}, {249.99465811606333, 0.060295628497261784}, {257.67367491826326, 0.06204445812034564}, {265.35269172046316, 0.06379623061886201}, {273.03170852266305, 0.0655506976876963}, {280.71072532486295, 0.06730763819189838}, {288.38974212706285, 0.06906685454935309}, {296.06875892926274, 0.07082816967637794}, {303.74777573146264, 0.07259142439662915}, {311.42679253366254, 0.07435647523334749}, {319.10580933586243, 0.07612319252037089}, {326.78482613806233, 0.07789145877947942}, {334.4638429402622, 0.07966116732126927}, {342.1428597424621, 0.08143222103443767}, {349.821876544662, 0.08320453133452801}, {357.5008933468619, 0.0849780172481587}, {365.1799101490618, 0.0867526046127933}, {372.8589269512617, 0.08852822537539494}, {380.5379437534616, 0.09030481697599702}, {388.2169605556615, 0.09208232180443249}, {395.8959773578614, 0.0938606867202886}, {403.5749941600613, 0.09563986262766612}, {411.2540109622612, 0.0974198040975794}, {418.9330277644611, 0.0992004690318849}, {426.612044566661, 0.10098181836350544}});
    model.result().table("tbl1").label("\u963b\u5c3c\u6bd4\u56fe");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u963b\u5c3c\u6bd4\u56fe");
    model.result("pg3").create("tblp11", "Table");
    model.result("pg3").feature("tblp11").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp11").set("table", "tbl1");
    model.result("pg3").feature("tblp11").label("\u963b\u5c3c 1");
    model.result("pg3").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg3").set("ylabel", "\u963b\u5c3c\u6bd4");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/beam", true);
    model.study("std2").create("frmod", "Frequencymodal");
    model.study("std2").feature("frmod").set("outputmap", new String[]{});
    model.study("std2").feature("frmod").setSolveFor("/physics/beam", true);
    model.study("std2").label("\u8c10\u6ce2\u5f3a\u8feb\u632f\u52a8");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("frmod").set("plist", "range(0,2,38) range(40,5e-2,45) range(46,2,60)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset2");
    model.result().dataset("cpt1").set("pointx", "L/2");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8c10\u6ce2\u5cf0\u503c\u4f4d\u79fb");
    model.result("pg4").set("data", "cpt1");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").set("expr", "beam.uAmpZ");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8c10\u6ce2\u5cf0\u503c\u5e94\u529b");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("expr", "abs(beam.sb1)");
    model.result("pg5").run();
    model.result().numerical().create("max1", "MaxLine");
    model.result().numerical("max1").label("\u8c10\u6ce2\u5cf0\u503c\u4f4d\u79fb");
    model.result().numerical("max1").set("data", "dset2");
    model.result().numerical("max1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("max1").setIndex("looplevel", new int[]{74}, 0);
    model.result().numerical("max1").selection().set(1);
    model.result().numerical("max1").setIndex("expr", "beam.uAmpZ", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8c10\u6ce2\u5cf0\u503c\u4f4d\u79fb");
    model.result().numerical("max1").set("table", "tbl2");
    model.result().numerical("max1").setResult();
    model.result().numerical().duplicate("max2", "max1");
    model.result().numerical("max2").label("\u8c10\u6ce2\u5cf0\u503c\u5e94\u529b");
    model.result().numerical("max2").setIndex("expr", "abs(beam.sb1)", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u8c10\u6ce2\u5cf0\u503c\u5e94\u529b");
    model.result().numerical("max2").set("table", "tbl3");
    model.result().numerical("max2").setResult();

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "phase");
    model.component("comp1").func("int1").setIndex("table", 20, 0, 0);
    model.component("comp1").func("int1").setIndex("table", "-pi/2", 0, 1);
    model.component("comp1").func("int1").setIndex("table", 60, 1, 0);
    model.component("comp1").func("int1").setIndex("table", "pi/2", 1, 1);
    model.component("comp1").func("int1").set("interp", "neighbor");
    model.component("comp1").func("int1").setIndex("argunit", "Hz", 0);
    model.component("comp1").func("int1").setIndex("fununit", "rad", 0);

    model.component("comp1").physics("beam").create("el2", "EdgeLoad", 1);
    model.component("comp1").physics("beam").feature("el2").selection().set(1);
    model.component("comp1").physics("beam").feature("el2")
         .set("forceReferenceLength", new String[]{"0", "0", "F0"});
    model.component("comp1").physics("beam").feature("el2").create("ph1", "Phase", 1);
    model.component("comp1").physics("beam").feature("el2").feature("ph1")
         .set("FPh", new String[]{"0", "0", "phase(freq)"});

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/beam", true);
    model.study("std3").feature("freq").set("plist", "20 60");
    model.study("std3").feature("freq").set("useadvanceddisable", true);
    model.study("std3").feature("freq").set("disabledphysics", new String[]{"beam/el1"});
    model.study("std3").create("ftfft", "FreqToTimeFFT");
    model.study("std3").feature("ftfft").set("fftouttrange", "range(0.0,1/(200*20),1/20)");
    model.study("std3").feature("ftfft").set("fftscaling", "discrete");
    model.study("std3").feature("ftfft").set("useadvanceddisable", true);
    model.study("std3").showAutoSequences("all");

    model.sol("sol4").feature("fft1").set("control", "user");

    model.study("std3").label("\u5468\u671f\u6027\u5f3a\u8feb\u632f\u52a8");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("cpt2", "CutPoint3D");
    model.result().dataset("cpt2").set("data", "dset4");
    model.result().dataset("cpt2").set("pointx", "L/2");
    model.result().dataset("cpt2").set("pointy", 0);
    model.result().dataset("cpt2").set("pointz", 0);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5468\u671f\u6027\u5cf0\u503c\u4f4d\u79fb");
    model.result("pg6").set("data", "cpt2");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("expr", "w");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u5468\u671f\u6027\u5cf0\u503c\u5e94\u529b");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("expr", "beam.sb1");
    model.result("pg7").run();
    model.result().numerical().create("max3", "MaxLine");
    model.result().numerical("max3").label("\u5468\u671f\u6027\u5cf0\u503c\u4f4d\u79fb");
    model.result().numerical("max3").set("data", "dset4");
    model.result().numerical("max3").selection().set(1);
    model.result().numerical("max3").setIndex("expr", "abs(w)", 0);
    model.result().numerical("max3").set("dataseries", "maximum");
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u5468\u671f\u6027\u5cf0\u503c\u4f4d\u79fb");
    model.result().numerical("max3").set("table", "tbl4");
    model.result().numerical("max3").setResult();
    model.result().numerical().duplicate("max4", "max3");
    model.result().numerical("max4").label("\u5468\u671f\u6027\u5cf0\u503c\u5e94\u529b");
    model.result().numerical("max4").setIndex("expr", "abs(beam.sb1)", 0);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5468\u671f\u6027\u5cf0\u503c\u5e94\u529b");
    model.result().numerical("max4").set("table", "tbl5");
    model.result().numerical("max4").setResult();

    model.component("comp1").physics("beam").create("el3", "EdgeLoad", 1);
    model.component("comp1").physics("beam").feature("el3").selection().all();
    model.component("comp1").physics("beam").feature("el3")
         .set("forceReferenceLength", new String[]{"0", "0", "F0"});

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/beam", true);
    model.study("std4").feature("time").set("tlist", "range(0,1e-4,1.2e-2) range(1.3e-2,1e-3,1.2)");
    model.study("std4").feature("time").set("useadvanceddisable", true);
    model.study("std4").feature("time").set("disabledphysics", new String[]{"beam/el1", "beam/el2"});
    model.study("std4").label("\u77ac\u6001\u5f3a\u8feb\u632f\u52a8");
    model.study("std4").setGenPlots(false);
    model.study("std4").showAutoSequences("all");

    model.sol("sol6").feature("v1").feature("comp1_u").set("scaleval", "1e-4");
    model.sol("sol6").feature("t1").set("tstepsgenalpha", "intermediate");
    model.sol("sol6").runAll();

    model.result().dataset().create("cpt3", "CutPoint3D");
    model.result().dataset("cpt3").set("data", "dset6");
    model.result().dataset("cpt3").set("pointx", "L/2");
    model.result().dataset("cpt3").set("pointy", 0);
    model.result().dataset("cpt3").set("pointz", 0);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u77ac\u6001\u5cf0\u503c\u4f4d\u79fb");
    model.result("pg8").set("data", "cpt3");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("xlabelactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").set("xlabel", "\u65f6\u95f4 (s)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u4f4d\u79fb (mm)");
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", "-1e-2");
    model.result("pg8").set("xmax", 0.5);
    model.result("pg8").set("ymin", "-1e-2");
    model.result("pg8").set("ymax", 1.1);
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").set("expr", "w");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().numerical().create("max5", "MaxLine");
    model.result().numerical("max5").label("\u77ac\u6001\u5f3a\u8feb\u632f\u52a8 - \u6700\u5927\u4f4d\u79fb");
    model.result().numerical("max5").set("data", "dset6");
    model.result().numerical("max5").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("max5").setIndex("looplevel", new int[]{118}, 0);
    model.result().numerical("max5").selection().set(1);
    model.result().numerical("max5").setIndex("expr", "w", 0);
    model.result().numerical("max5").set("dataseries", "maximum");
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u77ac\u6001\u5f3a\u8feb\u632f\u52a8 - \u6700\u5927\u4f4d\u79fb");
    model.result().numerical("max5").set("table", "tbl6");
    model.result().numerical("max5").setResult();
    model.result().numerical().duplicate("max6", "max5");
    model.result().numerical("max6").label("\u77ac\u6001\u5f3a\u8feb\u632f\u52a8 - \u6700\u5927\u5e94\u529b");
    model.result().numerical("max6").setIndex("expr", "abs(beam.sb1)", 0);
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("\u77ac\u6001\u5f3a\u8feb\u632f\u52a8 - \u6700\u5927\u5e94\u529b");
    model.result().numerical("max6").set("table", "tbl7");
    model.result().numerical("max6").setResult();
    model.result().numerical().duplicate("max7", "max5");
    model.result().numerical("max7").label("\u77ac\u6001\u5f3a\u8feb\u632f\u52a8 - \u9759\u6001\u4f4d\u79fb");
    model.result().numerical("max7").setIndex("looplevelinput", "interp", 0);
    model.result().numerical("max7").setIndex("interp", "range(1.1,1e-3,1.2)", 0);
    model.result().numerical("max7").set("dataseries", "average");
    model.result().table().create("tbl8", "Table");
    model.result().table("tbl8").comments("\u77ac\u6001\u5f3a\u8feb\u632f\u52a8 - \u9759\u6001\u4f4d\u79fb");
    model.result().numerical("max7").set("table", "tbl8");
    model.result().numerical("max7").setResult();

    model.study("std1").feature("eig").set("useadvanceddisable", true);
    model.study("std1").feature("eig").set("disabledphysics", new String[]{"beam/emm1/dmp1"});
    model.study("std2").feature("frmod").set("useadvanceddisable", true);
    model.study("std2").feature("frmod").set("disabledphysics", new String[]{"beam/el2", "beam/el3"});
    model.study("std3").feature("freq").set("disabledphysics", new String[]{"beam/el1", "beam/el3"});
    model.study("std3").feature("ftfft").set("disabledphysics", new String[]{"beam/el3"});

    model.result("pg7").run();

    model.title("\u6df1\u6881\u632f\u52a8\u5206\u6790");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u6df1\u6881\u7684\u81ea\u7531\u632f\u52a8\u548c\u5f3a\u8feb\u632f\u52a8\uff0c\u4f7f\u7528\u94c1\u6728\u8f9b\u67ef\u6881\u8ba1\u7b97\u4e86\u7279\u5f81\u9891\u7387\u3001\u9891\u7387\u54cd\u5e94\u548c\u77ac\u6001\u5206\u6790\u7684\u89e3\uff0c\u5e76\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u89e3\u6790\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("vibrating_deep_beam.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
