/*
 * bracket_optimization_llsw.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:48 by COMSOL 6.3.0.290. */
public class bracket_optimization_llsw {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_SOLIDWORKS\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("conrad", "1");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").activate("solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkSOLIDWORKS");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();

    model.component("comp1").view("view1").camera().set("zoomanglefull", 9);
    model.component("comp1").view("view1").camera().setIndex("position", -600, 0);
    model.component("comp1").view("view1").camera().setIndex("position", 36, 1);
    model.component("comp1").view("view1").camera().set("position", new int[]{-600, 36, 35});
    model.component("comp1").view("view1").camera().setIndex("target", 32.5, 0);
    model.component("comp1").view("view1").camera().set("target", new double[]{32.5, 36, 35});
    model.component("comp1").view("view1").camera().setIndex("up", 0, 0);
    model.component("comp1").view("view1").camera().setIndex("up", 0, 1);
    model.component("comp1").view("view1").camera().set("up", new int[]{0, 0, 1});
    model.component("comp1").view("view1").camera().setIndex("viewoffset", 0.03, 0);
    model.component("comp1").view("view1").camera().set("viewoffset", new double[]{0.03, 0.03});
    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.component("comp1").geom("geom1").run("fin");

    model.param().remove("LL_BDIA");
    model.param().remove("LL_DCMP");
    model.param().remove("LL_LX");
    model.param().remove("LL_LZ");
    model.param().remove("LL_RC");
    model.param().remove("LL_RO");
    model.param().remove("LL_THK");
    model.param().remove("LL_YOO");
    model.param().remove("LL_ZCO");
    model.param().remove("LL_ZOO");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("LL_THK", "4[mm]", "Plate thickness");
    model.param().set("LL_LX", "65[mm]", "Bracket length");
    model.param().set("LL_LZ", "70[mm]", "Bracket height");
    model.param().set("LL_DCMP", "40[mm]", "Diameter of component");
    model.param().set("LL_BDIA", "8[mm]", "Diameter of mounting bolts");
    model.param().set("LL_RC", "4[mm]", "Radius of central hole");
    model.param().set("LL_ZCO", "5[mm]", "Distance from bend to bottom of central hole");
    model.param().set("LL_RO", "5[mm]", "Radius of outer holes");
    model.param().set("LL_YOO", "8[mm]", "Distance from edge to outer hole");
    model.param().set("LL_ZOO", "20[mm]", "Distance from bend to bottom of outer hole");
    model.param().set("IXCmp", "7.1e-4[kg*m^2]", "Component mass moment of inertia aROund X axis");
    model.param().set("IYZCmp", "9.3e-4[kg*m^2]", "Component mass moment of inertia aROund Y and Z axes");
    model.param()
         .set("d_O_Cmp", "sqrt(((LL_YOO+LL_RO)-(4*LL_BDIA+LL_DCMP)/2)^2+((3*LL_THK+LL_ZOO+LL_RO)-LL_LZ)^2)-LL_RO-LL_DCMP/2", "Distance between edges of outer hole and component");
    model.param()
         .set("d_C_Cmp", "LL_LZ-(3*LL_THK+LL_ZCO+LL_RC)-LL_RC-LL_DCMP/2", "Distance between edges of central hole and component");
    model.param()
         .set("d_O_C", "sqrt(((LL_YOO+LL_RO)-(4*LL_BDIA+LL_DCMP)/2)^2+((3*LL_THK+LL_ZOO+LL_RO)-(3*LL_THK+LL_ZCO+LL_RC))^2)-LL_RO-LL_RC", "Distance between edges of outer and central holes");
    model.param().set("d_O_O", "2*((4*LL_BDIA+LL_DCMP)/2-(LL_YOO+LL_RO)-LL_RO)", "Distance between outer holes");
    model.param().set("mCmp", "2.4[kg]", "Component mass");
    model.param().set("maxStressLimit", "80[MPa]", "Maximum allowed equivalent stress under 4g acceleration");
    model.param().set("minFreq", "60[Hz]", "Lowest allowed natural frequency");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup().create("Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
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
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "200e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.30");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").label("Lam\u00e9 parameters");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("lambLame", "1.15e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Lame").set("muLame", "7.69e10[Pa]");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("lighting", "cooktorrance");
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("specular", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noisecolor", "custom");
    model.component("comp1").material("mat1").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp1").material("mat1").set("noisescale", 0);
    model.component("comp1").material("mat1").set("noise", "off");
    model.component("comp1").material("mat1").set("noisefreq", 1);
    model.component("comp1").material("mat1").set("normalnoisebrush", "0");
    model.component("comp1").material("mat1").set("normalnoisetype", "0");
    model.component("comp1").material("mat1").set("alpha", 1);

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_cad1_Fixed_Bolts_bnd");
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").selection()
         .named("geom1_cad1_Rigid_Connector_Mounted_comp_bnd");
    model.component("comp1").physics("solid").feature("rig1").set("CenterOfRotationType", "userDefined");
    model.component("comp1").physics("solid").feature("rig1")
         .set("xc", new String[]{"LL_LX-LL_THK/2", "(4*LL_BDIA+LL_DCMP)/2", "LL_LZ"});
    model.component("comp1").physics("solid").feature("rig1").create("rmm1", "RigidBodyMassInertia", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rmm1").set("mt", "mCmp");
    model.component("comp1").physics("solid").feature("rig1").feature("rmm1")
         .set("mi", new String[]{"IXCmp", "0", "0", "0", "IYZCmp", "0", "0", "0", "IYZCmp"});

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_cad1_Boundary_Mesh_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", 0.2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurve", 0.38);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "freq*2*pi", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
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

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup("std1mpf1").run();

    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 3);
    model.component("comp1").physics("solid").feature("bl1").selection().all();
    model.component("comp1").physics("solid").feature("bl1")
         .set("FperVol", new String[]{"4*g_const*solid.rho", "4*g_const*solid.rho", "4*g_const*solid.rho"});
    model.component("comp1").physics("solid").feature("rig1").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig1").feature("rf1")
         .set("Ft", new String[]{"4*g_const*mCmp", "4*g_const*mCmp", "4*g_const*mCmp"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").activate("solid", true);

    model.sol().create("sol2");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "1");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "1");

    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.mises"});
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.nodeGroup().create("dset2solidlgrp", "Results");
    model.nodeGroup("dset2solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset2solidlgrp").set("type", "plotgroup");
    model.nodeGroup("dset2solidlgrp").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("dset2solidlgrp", 0);

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u4f53\u79ef\u8f7d\u8377 (solid)");

    model.nodeGroup("dset2solidlgrp").add("plotgroup", "pg3");

    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg3").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", 0);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46);
    model.result("pg3").feature("surf1").create("tran1", "Transparency");
    model.result("pg3").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1")
         .set("expr", new String[]{"solid.bl1.F_Vx", "solid.bl1.F_Vy", "solid.bl1.F_Vz"});
    model.result("pg3").feature("arwv1").set("placement", "gausspoints");
    model.result("pg3").feature("arwv1").set("arrowbase", "tail");
    model.result("pg3").feature("arwv1").label("\u4f53\u8f7d\u8377 1");
    model.result("pg3").feature().move("surf1", 1);
    model.result("pg3").feature("arwv1").set("inheritplot", "none");
    model.result("pg3").feature("arwv1").create("col", "Color");
    model.result("pg3").feature("arwv1").feature("col").set("expr", "solid.bl1.F_V_Mag");
    model.result("pg3").feature("arwv1").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("arwv1").feature("col").set("topcolor", "red");
    model.result("pg3").feature("arwv1").feature("col").set("bottomcolor", "custom");
    model.result("pg3").feature("arwv1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg3").feature("arwv1").set("color", "magenta");
    model.result("pg3").feature("arwv1").create("def", "Deform");
    model.result("pg3").feature("arwv1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("arwv1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("arwv1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("arwv1").feature("def").set("scale", 0);

    model.nodeGroup("dset2solidlgrp").placeAfter("plotgroup", "pg2");
    model.nodeGroup().move("dset2solidlgrp", 0);

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u5168\u5c40\u8f7d\u8377 (solid)");

    model.nodeGroup("dset2solidlgrp").add("plotgroup", "pg4");

    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg4").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("surf1").feature("def").set("scale", 0);
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46);
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg4").create("pttraj1", "PointTrajectories");
    model.result("pg4").feature("pttraj1").set("plotdata", "global");
    model.result("pg4").feature("pttraj1")
         .set("expr", new String[]{"solid.rig1.rf1.loadposx", "solid.rig1.rf1.loadposy", "solid.rig1.rf1.loadposz"});
    model.result("pg4").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg4").feature("pttraj1")
         .set("arrowexpr", new String[]{"solid.rig1.rf1.Fx", "solid.rig1.rf1.Fy", "solid.rig1.rf1.Fz"});
    model.result("pg4").feature("pttraj1").set("linetype", "none");
    model.result("pg4").feature("pttraj1").set("arrowbase", "tail");
    model.result("pg4").feature("pttraj1").label("\u4f5c\u7528\u529b 1 (\u521a\u6027\u8fde\u63a5\u4ef6 1)");
    model.result("pg4").feature().move("surf1", 1);
    model.result("pg4").feature("pttraj1").set("inheritplot", "none");
    model.result("pg4").feature("pttraj1").create("col", "Color");
    model.result("pg4").feature("pttraj1").feature("col").set("expr", "solid.rig1.rf1.F_Mag");
    model.result("pg4").feature("pttraj1").feature("col").set("coloring", "gradient");
    model.result("pg4").feature("pttraj1").feature("col").set("topcolor", "red");
    model.result("pg4").feature("pttraj1").feature("col").set("bottomcolor", "custom");
    model.result("pg4").feature("pttraj1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg4").feature("pttraj1").set("pointcolor", "blue");

    model.sol("sol2").runAll();

    model.result("pg2").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("maxop1").selection().named("geom1_cad1_Maximum_Stress_bnd");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("mass", "intop1(solid.rho)", "\u8d28\u91cf");
    model.component("comp1").variable("var1").descr("mass", "Bracket mass");
    model.component("comp1").variable("var1").set("maxStress", "maxop1(solid.mises)");
    model.component("comp1").variable("var1").descr("maxStress", "Maximum stress");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "solid.mises");
    model.result("pg5").feature("vol1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("filt1").set("expr", "x>LL_LX-5*LL_THK");

    model.study().create("std3");
    model.study("std3").create("opt", "Optimization");
    model.study("std3").create("ref", "StudyReference");
    model.study("std3").feature("ref").set("studyref", "std1");
    model.study("std3").create("ref2", "StudyReference");
    model.study("std3").feature("ref2").set("studyref", "std2");
    model.study("std3").feature("opt").set("optsolver", "cobyla");
    model.study("std3").feature("opt").set("err", false);
    model.study("std3").feature("opt").setIndex("optobj", "comp1.mass", 0);
    model.study("std3").feature("opt").setIndex("optobjEvaluateFor", "ref2", 0);
    model.study("std3").feature("opt").set("opttol", 0.1);
    model.study("std3").feature("opt").set("objectivesolution", "first");
    model.study("std3").feature("opt").set("pname", new String[]{"LL_RC", "LL_ZCO", "LL_RO", "LL_YOO", "LL_ZOO"});
    model.study("std3").feature("opt").set("initval", new String[]{"4[mm]", "5[mm]", "5[mm]", "8[mm]", "20[mm]"});
    model.study("std3").feature("opt").set("scale", new String[]{"12[mm]", "22[mm]", "12[mm]", "26[mm]", "22[mm]"});
    model.study("std3").feature("opt").set("lbound", new String[]{"3[mm]", "1[mm]", "3[mm]", "3[mm]", "8[mm]"});
    model.study("std3").feature("opt").set("ubound", new String[]{"15[mm]", "23[mm]", "15[mm]", "29[mm]", "30[mm]"});
    model.study("std3").feature("opt").setIndex("constraintExpression", "real(comp1.solid.freq)", 0);
    model.study("std3").feature("opt").setIndex("constraintLbound", "minFreq", 0);
    model.study("std3").feature("opt").setIndex("constraintExpression", "comp1.maxStress/maxStressLimit", 1);
    model.study("std3").feature("opt").setIndex("constraintUbound", 1, 1);
    model.study("std3").feature("opt").setIndex("constraintEvaluateFor", "ref2", 1);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_O_Cmp", 2);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 2);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_C_Cmp", 3);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 3);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_O_C", 4);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 4);
    model.study("std3").feature("opt").setIndex("constraintExpression", "d_O_O", 5);
    model.study("std3").feature("opt").setIndex("constraintLbound", "3[mm]", 5);
    model.study("std3").feature("opt").set("plot", true);
    model.study("std3").feature("opt").set("plotgroup", "pg5");

    model.sol().create("sol3");
    model.sol("sol3").study("std3");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "1");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "1");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "1");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol3").create("copy1", "CopySolution");
    model.sol("sol3").feature("copy1").set("sol", "sol2");
    model.sol("sol3").attach("std3");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "1");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "1");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "1");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.batch().create("o1", "Optimization");
    model.batch("o1").study("std3");
    model.batch("p1").study("std3");
    model.batch("o1").attach("std3");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").attach("std3");
    model.batch("p1").set("optimization", "o1");
    model.batch("p1").set("err", "on");
    model.batch("p1").set("control", "opt");
    model.batch().create("p2", "Parametric");
    model.batch("p2").study("std3");
    model.batch("p2").create("so1", "Solutionseq");
    model.batch("p2").feature("so1").set("seq", "sol2");
    model.batch("p2").feature("so1").set("store", "on");
    model.batch("p2").feature("so1").set("clear", "on");
    model.batch("p2").feature("so1").set("psol", "none");
    model.batch("p2").attach("std3");
    model.batch("p2").set("optimization", "o1");
    model.batch("p2").set("err", "on");
    model.batch("p2").set("control", "opt");
    model.batch().create("p3", "Parametric");
    model.batch("p3").study("std3");
    model.batch("p3").create("so1", "Solutionseq");
    model.batch("p3").feature("so1").set("seq", "sol3");
    model.batch("p3").feature("so1").set("store", "on");
    model.batch("p3").feature("so1").set("clear", "on");
    model.batch("p3").feature("so1").set("psol", "none");
    model.batch("p3").attach("std3");
    model.batch("p3").set("optimization", "o1");
    model.batch("p3").set("err", "on");
    model.batch("p3").set("control", "opt");
    model.batch("o1").set("parametricjobs", new String[]{"p1", "p2", "p3"});

    model.sol("sol3").study("std3");
    model.sol("sol3").feature().remove("copy1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "1");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "1");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "1");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");
    model.sol("sol3").create("copy1", "CopySolution");
    model.sol("sol3").feature("copy1").set("sol", "sol2");
    model.sol("sol3").attach("std3");
    model.sol("sol1").study("std1");

    model.study("std1").feature("eig").set("notlistsolnum", 1);
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("listsolnum", 1);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("linplistsolnum", new String[]{"1"});
    model.study("std1").feature("eig").set("linpsolnum", "1");

    model.sol("sol1").feature().remove("e1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "1.2E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol2").study("std2");

    model.study("std2").feature("stat").set("notlistsolnum", 1);
    model.study("std2").feature("stat").set("notsolnum", "1");
    model.study("std2").feature("stat").set("listsolnum", 1);
    model.study("std2").feature("stat").set("solnum", "1");

    model.sol("sol2").feature().remove("s1");
    model.sol("sol2").feature().remove("v1");
    model.sol("sol2").feature().remove("st1");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_a_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_bcd_rig1").set("scaleval", "1e-2");
    model.sol("sol2").feature("v1").feature("comp1_solid_uvw_rig1").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-2*0.1196855546685592");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").attach("std2");

    model.batch("o1").active(true);
    model.batch("o1").attach("std3");
    model.batch("p1").feature().remove("so1");

    return model;
  }

  public static Model run3(Model model) {
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").attach("std3");
    model.batch("p1").set("optimization", "o1");
    model.batch("p1").set("err", "on");
    model.batch("p1").set("control", "opt");
    model.batch("p2").feature().remove("so1");
    model.batch("p2").create("so1", "Solutionseq");
    model.batch("p2").feature("so1").set("seq", "sol2");
    model.batch("p2").feature("so1").set("store", "on");
    model.batch("p2").feature("so1").set("clear", "on");
    model.batch("p2").feature("so1").set("psol", "none");
    model.batch("p2").attach("std3");
    model.batch("p2").set("optimization", "o1");
    model.batch("p2").set("err", "on");
    model.batch("p2").set("control", "opt");
    model.batch("p3").feature().remove("so1");
    model.batch("p3").create("so1", "Solutionseq");
    model.batch("p3").feature("so1").set("seq", "sol3");
    model.batch("p3").feature("so1").set("store", "on");
    model.batch("p3").feature("so1").set("clear", "on");
    model.batch("p3").feature("so1").set("psol", "none");
    model.batch("p3").attach("std3");
    model.batch("p3").set("optimization", "o1");
    model.batch("p3").set("err", "on");
    model.batch("p3").set("control", "opt");
    model.batch("o1").set("parametricjobs", new String[]{"p1", "p2", "p3"});

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p3").feature("so1").set("psol", "sol4");
    model.batch("o1").run();

    model.result("pg2").run();

    model.study("std3").feature("opt").set("probewindow", "");

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset3");
    model.result().numerical("gev1")
         .set("expr", new String[]{"LL_RC", "LL_ZCO", "LL_RO", "LL_YOO", "LL_ZOO", "opt.obj1"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"RC (Radius of central hole)", "ZCO (Distance from bend to bottom of central hole)", "RO (Radius of outer holes)", "YOO (Distance from edge to outer hole)", "ZOO (Distance from bend to bottom of outer hole)", "Bracket mass"});
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl7");
    model.result().numerical("gev1").setResult();
    model.result().numerical("gev1").set("table", "tbl7");
    model.result().numerical("gev1").appendResult();
    model.result("pg5").run();
    model.result("pg5").run();

    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(true, true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").run();

    model.result("pg5").run();

    model.title("\u652f\u67b6\u7684\u51e0\u4f55\u53c2\u6570\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u6700\u5927\u7a0b\u5ea6\u51cf\u5c11\u4e86\u901a\u8fc7 LiveLink\u2122 \u63a5\u53e3\u4ece SOLIDWORKS\u00ae \u540c\u6b65\u7684\u652f\u67b6\u7684\u8d28\u91cf\u3002\n\n\u5176\u4e2d\u7ed9\u51fa\u4e86\u6700\u4f4e\u56fa\u6709\u9891\u7387\u548c\u9759\u8f7d\u8377\u5de5\u51b5\u4e0b\u6700\u5927\u5e94\u529b\u7684\u6781\u9650\uff0c\u5e76\u901a\u8fc7\u6539\u53d8\u591a\u4e2a\u51e0\u4f55\u7279\u5f81\u7684\u5927\u5c0f\u548c\u4f4d\u7f6e\u6765\u4f18\u5316\u8d28\u91cf\uff0c\u540c\u65f6\u5f3a\u5236\u65bd\u52a0\u591a\u4e2a\u51e0\u4f55\u7ea6\u675f\u6765\u4fdd\u6301\u8bbe\u8ba1\u610f\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("bracket_optimization_llsw.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
