/*
 * magnetic_prospecting.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_prospecting {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Magnetostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mfnc", "MagnetostaticsNoCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", true);

    model.param().set("mur_magn", "3.5");
    model.param().descr("mur_magn", "\u78c1\u94c1\u77ff\u7684\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("c_magn", "0.25");
    model.param().descr("c_magn", "\u77ff\u5e8a\u4e2d\u7684\u78c1\u94c1\u77ff\u542b\u91cf");
    model.param().set("Mr_magn", "60[A/m]");
    model.param().descr("Mr_magn", "\u78c1\u94c1\u77ff\u4e2d\u7684\u5269\u78c1");
    model.param().set("H0", "48163[nT]/mu0_const");
    model.param().descr("H0", "\u5730\u78c1\u573a");
    model.param().set("Incl", "59.357[deg]");
    model.param().descr("Incl", "\u5f53\u5730\u78c1\u503e\u89d2");
    model.param().set("Decl", "12.275[deg]");
    model.param().descr("Decl", "\u5f53\u5730\u78c1\u504f\u89d2");

    model.variable().create("var1");
    model.variable("var1").set("Br_magn", "Mr_magn*mu0_const");
    model.variable("var1").descr("Br_magn", "\u78c1\u94c1\u77ff\u7684\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6");
    model.variable("var1").set("Br", "Br_magn*c_magn");
    model.variable("var1").descr("Br", "\u77ff\u5e8a\u7684\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6");
    model.variable("var1").set("Gx", "cos(Incl)*sin(Decl)");
    model.variable("var1").descr("Gx", "\u5730\u78c1\u573a\u65b9\u5411\uff0cx \u5206\u91cf");
    model.variable("var1").set("Gy", "cos(Incl)*cos(Decl)");
    model.variable("var1").descr("Gy", "\u5730\u78c1\u573a\u65b9\u5411\uff0cy \u5206\u91cf");
    model.variable("var1").set("Gz", "-sin(Incl)");
    model.variable("var1").descr("Gz", "\u5730\u78c1\u573a\u65b9\u5411\uff0cz \u5206\u91cf");
    model.variable("var1").set("mur_ore", "1+(mur_magn-1)*c_magn");
    model.variable("var1")
         .descr("mur_ore", "\u8868\u793a\u77ff\u5e8a\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\u7684\u6a21\u578b");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "magnetic_prospecting.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("elp1", "Ellipsoid");
    model.component("comp1").geom("geom1").feature("elp1").set("semiaxes", new int[]{1000, 200, 50});
    model.component("comp1").geom("geom1").feature("elp1").set("pos", new int[]{2500, 1500, 200});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mfnc").create("mfcs1", "MagneticFluxConservationSolid", 3);
    model.component("comp1").physics("mfnc").feature("mfcs1").selection().set(3);
    model.component("comp1").physics("mfnc").feature("mfcs1").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mfnc").feature("mfcs1")
         .set("normBr_crel_BH_RemanentFluxDensity_mat", "userdef");
    model.component("comp1").physics("mfnc").feature("mfcs1").set("normBr_crel_BH_RemanentFluxDensity", "Br");
    model.component("comp1").physics("mfnc").feature("mfcs1")
         .set("e_crel_BH_RemanentFluxDensity", new String[]{"Gx", "Gy", "Gz"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(3);
    model.component("comp1").material("mat1").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent_flux_density");
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"mur_ore"});
    model.component("comp1").material("mat1").label("\u94c1\u77ff");

    model.component("comp1").physics("mfnc").prop("BackgroundField").set("SolveFor", "ReducedField");
    model.component("comp1").physics("mfnc").prop("BackgroundField")
         .set("Hb", new String[]{"H0*Gx", "H0*Gy", "H0*Gz"});
    model.component("comp1").physics("mfnc").create("exfd1", "ExternalMagneticFluxDensity", 2);
    model.component("comp1").physics("mfnc").feature("exfd1").selection().set(1, 2, 3, 4, 5, 7, 8, 9, 18, 19);
    model.component("comp1").physics("mfnc").create("zsp1", "ZeroMagneticScalarPotential", 0);
    model.component("comp1").physics("mfnc").feature("zsp1").selection().set(7);

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(6);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "dset2");
    model.result("pg1").feature("surf1").set("expr", "mfnc.normredH");
    model.result("pg1").feature("surf1").set("descr", "\u7ea6\u5316\u78c1\u573a\u6a21");
    model.result("pg1").feature("surf1").set("expr", "down(mfnc.normredH)");
    model.result("pg1").feature("surf1").set("descractive", true);
    model.result("pg1").feature("surf1").set("descr", "\u7ea6\u5316\u78c1\u573a\u6a21\uff08\u4e0b\u4fa7\uff09");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickzmethod", "coord");
    model.result("pg1").feature("slc1").set("quickz", 1300);
    model.result("pg1").feature("slc1").set("expr", "mfnc.normredH");
    model.result("pg1").feature("slc1").set("descr", "\u7ea6\u5316\u78c1\u573a\u6a21");
    model.result("pg1").feature("slc1").set("colortable", "GrayBody");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "0");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"mfnc.redHx", "mfnc.redHy", "mfnc.redHz"});
    model.result("pg2").feature("arws1").set("descr", "\u7ea6\u5316\u78c1\u573a");
    model.result("pg2").feature("arws1").set("arrowcount", 2000);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("quickplane", "xy");
    model.result("pg3").feature("slc1").set("quickzmethod", "coord");
    model.result("pg3").feature("slc1").set("quickz", 200);
    model.result("pg3").feature("slc1").set("expr", "mfnc.normBr/(mu0_const*mfnc.normM)");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u94c1\u77ff\u5e8a\u7684\u78c1\u6cd5\u52d8\u63a2");

    model
         .description("\u78c1\u6cd5\u52d8\u63a2\u662f\u4e00\u79cd\u63a2\u67e5\u94c1\u77ff\u5e8a\u7684\u5730\u8d28\u52d8\u63a2\u65b9\u6cd5\u3002\u88ab\u52a8\u7684\u78c1\u6cd5\u52d8\u63a2\u4f9d\u8d56\u4e8e\u5bf9\u5c40\u90e8\u5730\u78c1\u5f02\u5e38\u7684\u7cbe\u786e\u6620\u5c04\u3002\u672c\u4f8b\u901a\u8fc7\u6c42\u89e3\u5730\u78c1\u573a\u5f15\u8d77\u7684\u94c1\u77ff\u4e2d\u7684\u611f\u5e94\u78c1\u5316\u5f3a\u5ea6\uff0c\u6765\u4f30\u8ba1\u5730\u8868\u548c\u822a\u7a7a\u52d8\u63a2\u7684\u78c1\u5f02\u5e38\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("magnetic_prospecting.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
