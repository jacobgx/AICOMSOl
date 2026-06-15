/*
 * viscoelastic_damper_frequency.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:06 by COMSOL 6.3.0.290. */
public class viscoelastic_damper_frequency {

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
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").insertFile("viscoelastic_damper_geom_sequence.mph", "geom1");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 3);
    model.component("comp1").physics("solid").feature("lemm2").set("IsotropicOption", "KG");
    model.component("comp1").physics("solid").feature("lemm2").set("MixedFormulation", "pFormulation");
    model.component("comp1").physics("solid").feature("lemm2").selection().set(2, 5);
    model.component("comp1").physics("solid").feature("lemm2").create("vis1", "Viscoelasticity", 3);
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1").set("Gvm", new int[][]{});
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1").set("tauvm", new int[][]{});
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1")
         .set("Gvm", new String[]{"13.3[MPa]", "286[MPa]", "291[MPa]", "212[MPa]", "112[MPa]", "61.6[MPa]", "29.8[MPa]", "16.1[MPa]", "7.83[MPa]", "4.15[MPa]", 
         "2.03[MPa]", "1.11[MPa]", "0.491[MPa]", "0.326[MPa]", "0.0825[MPa]", "0.126[MPa]", "0.0373[MPa]", "0.0118[MPa]"});
    model.component("comp1").physics("solid").feature("lemm2").feature("vis1")
         .set("tauvm", new String[]{"1e-7", "1e-6", "3.16e-6", "1e-5", "3.16e-5", "1e-4", "3.16e-4", "1e-3", "3.16e-3", "1e-2", 
         "3.16e-2", "1e-1", "3.16e-1", "1", "3.16", "10", "100", "1000"});

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
    model.component("comp1").material("mat2").label("\u7c98\u5f39\u6027");
    model.component("comp1").material("mat2").selection().set(2, 5);
    model.component("comp1").material("mat2").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat2").propertyGroup("KG").set("K", new String[]{"4e8"});
    model.component("comp1").material("mat2").propertyGroup("KG").set("G", new String[]{"5.86e4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1060"});

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_sel3");
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("geom1_sel2");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "8.5[MPa]"});
    model.component("comp1").physics("solid").feature("bndl1").create("ph1", "Phase", 2);
    model.component("comp1").physics("solid").feature("bndl1").feature("ph1")
         .set("FPh", new String[]{"0", "0", "pi/2"});
    model.component("comp1").physics("solid").create("disp2", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp2").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"0.5[MPa]", "0", "8.5[MPa]"});

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

    model.study("std1").feature("freq").set("plist", "range(0,0.125,0.5) range(1,0.5,5)");
    model.study("std1").showAutoSequences("all");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb\uff0c\u9891\u57df");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();

    model.study("std1").feature("freq").set("plot", true);

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 10, 0);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().dataset("cpt1").set("pointy", -10);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u50a8\u80fd\u548c\u635f\u8017\u6a21\u91cf");
    model.result("pg2").set("data", "cpt1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u50a8\u80fd\u6a21\u91cf\u548c\u635f\u8017\u6a21\u91cf");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("expr", "solid.Gstor/6.895");
    model.result("pg2").feature("ptgr1").set("linewidth", 2);
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "G<sub>stor</sub>/6.895", 0);
    model.result("pg2").run();
    model.result("pg2").create("ptgr2", "PointGraph");
    model.result("pg2").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr2").set("linewidth", "preference");
    model.result("pg2").feature("ptgr2").set("expr", "solid.Gloss/6.895");
    model.result("pg2").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg2").feature("ptgr2").set("linewidth", 2);
    model.result("pg2").feature("ptgr2").set("legend", true);
    model.result("pg2").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr2").setIndex("legends", "G<sub>loss</sub>/6.895", 0);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("ftfft", "FreqToTimeFFT");
    model.study("std2").feature("ftfft").set("fftinputstudy", "std1");
    model.study("std2").feature("ftfft").set("fftouttrange", "range(0,1/(3*30), 1/3)");
    model.study("std2").feature("ftfft").set("fftwindowinv", true);
    model.study("std2").feature("ftfft").set("fftwintypeinv", "rectangle");
    model.study("std2").feature("ftfft").set("fftwinmininv", 2.9);
    model.study("std2").feature("ftfft").set("fftwinmaxinv", 3.1);
    model.study("std2").feature("ftfft").set("fftscaling", "discrete");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Fz1", "8.5[MPa]*cos(2*pi*t*3[Hz])*pi*16[mm]*10[mm]");
    model.component("comp1").variable("var1").descr("Fz1", "\u603b\u529b\uff0cZ \u5206\u91cf");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb\uff0c\u65f6\u57df");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("expr", "w");
    model.result("pg3").feature("vol1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6ede\u56de\u73af");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(25);
    model.result("pg4").feature("ptgr1").set("expr", "Fz1");
    model.result("pg4").feature("ptgr1").set("unit", "kN");
    model.result("pg4").feature("ptgr1").set("xdata", "expr");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "w");
    model.result("pg4").feature("ptgr1").set("titletype", "manual");
    model.result("pg4").feature("ptgr1").set("title", "\u6ede\u56de\u73af");
    model.result("pg4").run();

    model.title("\u9ecf\u5f39\u6027\u7ed3\u6784\u963b\u5c3c\u5668");

    model
         .description("\u672c\u4f8b\u4e3a\u9ecf\u5f39\u6027\u963b\u5c3c\u5668\u6267\u884c\u9891\u7387\u54cd\u5e94\u5206\u6790\u3002\u8fd9\u79cd\u963b\u5c3c\u5668\u65e8\u5728\u51cf\u5c0f\u5efa\u7b51\u7269\u548c\u5176\u4ed6\u9ad8\u5c42\u7ed3\u6784\u7684\u98ce\u632f\u548c\u5730\u9707\u632f\u52a8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("viscoelastic_damper_frequency.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
