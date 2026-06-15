/*
 * viscoelastic_damper_transient.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:06 by COMSOL 6.3.0.290. */
public class viscoelastic_damper_transient {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Dynamics_and_Vibration");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").insertFile("viscoelastic_damper_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 3);
    model.component("comp1").physics("solid").feature("lemm2").set("IsotropicOption", "KG");
    model.component("comp1").physics("solid").feature("lemm2").set("MixedFormulation", "pFormulation");
    model.component("comp1").physics("solid").feature("lemm2").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm2").selection().set(2, 5);
    model.component("comp1").physics("solid").feature("lemm2").create("vis1", "Viscoelasticity", 3);
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1")
         .label("\u9ecf\u5f39\u6027\uff08\u65e0\u526a\u679d\uff09");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1").set("Gvm", new int[][]{});
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1").set("tauvm", new int[][]{});
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1")
         .set("Gvm", new String[]{"13.3[MPa]", "286[MPa]", "291[MPa]", "212[MPa]", "112[MPa]", "61.6[MPa]", "29.8[MPa]", "16.1[MPa]", "7.83[MPa]", "4.15[MPa]", 
         "2.03[MPa]", "1.11[MPa]", "0.491[MPa]", "0.326[MPa]", "0.0825[MPa]", "0.126[MPa]", "0.0373[MPa]", "0.0118[MPa]"});
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1")
         .set("tauvm", new String[]{"1e-7", "1e-6", "3.16e-6", "1e-5", "3.16e-5", "1e-4", "3.16e-4", "1e-3", "3.16e-3", "1e-2", 
         "3.16e-2", "1e-1", "3.16e-1", "1", "3.16", "10", "100", "1000"});
    model.component("comp1").physics("solid").feature("lemm2").feature().duplicate("vis2", "vis1");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2")
         .label("\u9ecf\u5f39\u6027\uff08\u526a\u679d\uff09");
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2").set("pruneBranches", true);
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2").set("f_lower", 0.3);
    model.component("comp1").physics("solid").feature("lemm2").feature("vis2").set("f_upper", 30);

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u9ecf\u5f39\u6027");
    model.component("comp1").material("mat2").selection().set(2, 5);
    model.component("comp1").material("mat2").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat2").propertyGroup("KG").set("K", new String[]{"4e8"});
    model.component("comp1").material("mat2").propertyGroup("KG").set("G", new String[]{"5.86e4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1060"});

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("location", "0.02[s]");
    model.component("comp1").func("rm1").set("slope", 10);
    model.component("comp1").func("rm1").set("smoothzonelocactive", true);
    model.component("comp1").func("rm1").set("smoothzoneloc", 0.01);
    model.component("comp1").func("rm1").set("cutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonecutoffactive", true);
    model.component("comp1").func("rm1").set("smoothzonecutoff", 0.01);

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_sel3");
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "8.5[MPa]*sin(pi/2+2*pi*t*3[Hz])*rm1(t)"});
    model.component("comp1").physics("solid").create("disp2", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp2").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0.5[MPa]*sin(2*pi*t*3[Hz])*rm1(t)", "0", "8.5[MPa]*sin(2*pi*t*3[Hz])*rm1(t)"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("frame", "material");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_sel1");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Fz1", "intop1(solid.FperAreaz)");
    model.component("comp1").variable("var1").descr("Fz1", "\u603b\u529b Z \u5206\u91cf");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(30);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("fq1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("dis1").selection().set(65);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("fq2", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq2").selection().set(2, 61);
    model.component("comp1").mesh("mesh1").feature("fq2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq2").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(1, 2, 4);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("cpd1", "CopyDomain");
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").geom(3);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("source").set(1, 2, 7);
    model.component("comp1").mesh("mesh1").feature("cpd1").selection("destination").set(5, 6, 8);
    model.component("comp1").mesh("mesh1").create("fq3", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq3").selection().set(10);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u65e0\u526a\u679d");
    model.study("std1").feature("time").set("tlist", "range(0, 0.01, 3)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"solid/lemm2/vis2"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_lemm2_pw").set("scaleval", "1e6");
    model.sol("sol1").feature("t1").set("estrat", "exclude");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb\uff08\u65e0\u526a\u679d\uff09");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();

    model.study("std1").feature("time").set("plot", true);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("viscoelastic_damper_frequency_solution.txt");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6ede\u56de\u73af");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Fz1 [kN]");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "w [mm]");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("linestyle", "dashed");
    model.result("pg2").feature("tblp1").set("linewidth", 2);
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u9891\u57df\u89e3", 0);
    model.result("pg2").run();
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(25);
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "w");
    model.result("pg2").feature("ptgr1").set("xdatadescr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg2").feature("ptgr1").set("expr", "Fz1");
    model.result("pg2").feature("ptgr1").set("unit", "kN");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u65f6\u57df\u89e3\uff08\u65e0\u526a\u679d\uff09", 0);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u526a\u679d");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tlist", "range(0, 0.01, 3)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"solid/lemm2/vis1"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_solid_lemm2_pw").set("scaleval", "1e6");
    model.sol("sol2").feature("t1").set("estrat", "exclude");
    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("data", "dset2");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u65f6\u57df\u89e3\uff08\u526a\u679d\uff09", 0);
    model.result("pg2").run();

    model.title("\u9ecf\u5f39\u6027\u7ed3\u6784\u963b\u5c3c\u5668 - \u77ac\u6001\u5206\u6790");

    model
         .description("\u672c\u4f8b\u5bf9\u9ecf\u5f39\u6027\u963b\u5c3c\u5668\u6267\u884c\u77ac\u6001\u5206\u6790\u3002\u8fd9\u79cd\u963b\u5c3c\u5668\u65e8\u5728\u51cf\u5c0f\u5efa\u7b51\u7269\u548c\u5176\u4ed6\u9ad8\u5c42\u7ed3\u6784\u7684\u98ce\u632f\u548c\u5730\u9707\u632f\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("viscoelastic_damper_transient.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
