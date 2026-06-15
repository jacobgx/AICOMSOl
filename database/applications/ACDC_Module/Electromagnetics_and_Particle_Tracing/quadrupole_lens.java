/*
 * quadrupole_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class quadrupole_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mfnc", "MagnetostaticsNoCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "quadrupole_lens.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("M", "11", "\u79bb\u5b50\u8d28\u91cf\u6570");
    model.param().set("Zi", "5", "\u79bb\u5b50\u7535\u8377\u6570");
    model.param().set("vz", "0.01*3e8[m/s]", "\u79bb\u5b50\u901f\u5ea6");
    model.param().set("mp", "1.672e-27[kg]", "\u8d28\u5b50\u8d28\u91cf");
    model.param().set("Ze", "1.602e-19[C]", "\u8d28\u5b50\u7535\u8377");
    model.param().set("m", "M*mp", "\u79bb\u5b50\u8d28\u91cf");
    model.param().set("q", "Zi*Ze", "\u79bb\u5b50\u7535\u8377");
    model.param().set("Br", "8[mT]", "\u56db\u6781\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mfnc").create("mfcs1", "MagneticFluxConservationSolid", 3);
    model.component("comp1").physics("mfnc").feature("mfcs1").selection()
         .set(1, 2, 3, 5, 6, 7, 8, 9, 10, 14, 15, 16, 17, 18, 19);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94c1");
    model.component("comp1").material("mat1").selection().set(1, 2, 3);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"4000"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u78c1\u4f53");
    model.component("comp1").material("mat2").selection().set(5, 6, 7, 8, 9, 10, 14, 15, 16, 17, 18, 19);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent_flux_density");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").set("normBr", new String[]{"Br"});

    model.component("comp1").physics("mfnc").create("mag1", "Magnet", 3);
    model.component("comp1").physics("mfnc").feature("mag1").selection()
         .set(5, 6, 7, 8, 9, 10, 14, 15, 16, 17, 18, 19);
    model.component("comp1").physics("mfnc").feature("mag1").set("PatternType", "CircularPattern");
    model.component("comp1").physics("mfnc").feature("mag1").set("PeriodicType", "Alternating");
    model.component("comp1").physics("mfnc").feature("mag1").feature("north1").selection().set(18, 56, 58);
    model.component("comp1").physics("mfnc").feature("mag1").feature("south1").selection().set(15, 21, 57);
    model.component("comp1").physics("mfnc").create("zsp1", "ZeroMagneticScalarPotential", 0);
    model.component("comp1").physics("mfnc").feature("zsp1").selection().set(1);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u78c1\u573a");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").feature("slc1").set("expr", "mfnc.Hx");
    model.result("pg1").feature("slc1").set("descr", "\u78c1\u573a\uff0cx \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"mfnc.Hx", "mfnc.Hy", "mfnc.Hz"});
    model.result("pg1").feature("arwv1").set("descr", "\u78c1\u573a");
    model.result("pg1").feature("arwv1").set("xnumber", 4);
    model.result("pg1").feature("arwv1").set("ynumber", 4);
    model.result("pg1").feature("arwv1").set("znumber", 20);
    model.result("pg1").feature("arwv1").set("color", "black");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u79bb\u5b50\u8f68\u8ff9");
    model.result("pg2").create("ptr1", "ParticleMass");
    model.result("pg2").feature("ptr1").setIndex("const", "q", 0, 1);
    model.result("pg2").feature("ptr1").set("mass", "m");
    model.result("pg2").feature("ptr1").set("velstartz", "vz");
    model.result("pg2").feature("ptr1").set("xcoord", "0.03*cos(range(0,0.05*pi,2*pi))");
    model.result("pg2").feature("ptr1").set("ycoord", "0.03*sin(range(0,0.05*pi,2*pi))");
    model.result("pg2").feature("ptr1").set("zcoord", 0.01);
    model.result("pg2").feature("ptr1").set("linetype", "tube");
    model.result("pg2").feature("ptr1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("ptr1").set("radiusexpr", "0.001");
    model.result("pg2").feature("ptr1").set("rtol", "1e-6");
    model.result("pg2").run();
    model.result("pg2").feature("ptr1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("ptr1").feature("col1").set("expr", "q*vz*mfnc.normB");
    model.result("pg2").run();

    model.component("comp1").view("view1").label("\u9ed8\u8ba4\u89c6\u56fe");
    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").label("\u7f29\u653e\u89c6\u56fe");
    model.component("comp1").view("view2").camera().set("viewscaletype", "automatic");

    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "view1");
    model.result("pg1").run();
    model.result("pg2").run();

    model.title("\u56db\u6781\u900f\u955c");

    model
         .description("\u672c\u4f8b\u8ddf\u8e2a\u4e86\u4e00\u675f\u52a0\u901f\u7684 B5+ \u79bb\u5b50\u7a7f\u8fc7\u4e09\u4e2a\u78c1\u56db\u6781\u900f\u955c\u65f6\u7684\u8fc7\u7a0b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("quadrupole_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
