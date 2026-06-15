/*
 * permanent_magnet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class permanent_magnet {

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

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "permanent_magnet.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.25, 0.1, 0.1});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.1, 0, 0});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("blk1", "imp1");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "blk1+imp1*blk1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("mfnc").create("mfcs1", "MagneticFluxConservationSolid", 3);
    model.component("comp1").physics("mfnc").feature("mfcs1").selection().set(2, 3, 4);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94c1");
    model.component("comp1").material("mat1").selection().set(2, 4);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"4000"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat2").label("N54 (Sintered NdFeB)");
    model.component("comp1").material("mat2").set("family", "chrome");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").set("normBr", "1.47[T]");
    model.component("comp1").material("mat2").selection().set(3);

    model.component("comp1").physics("mfnc").create("mag1", "Magnet", 3);
    model.component("comp1").physics("mfnc").feature("mag1").selection().set(3);
    model.component("comp1").physics("mfnc").feature("mag1").feature("north1").selection().set(17);
    model.component("comp1").physics("mfnc").feature("mag1").feature("south1").selection().set(12);
    model.component("comp1").physics("mfnc").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("mfnc").feature("symp1").selection().set(2, 8, 24);
    model.component("comp1").physics("mfnc").feature("symp1").set("Symmetry_type", "Antisymmetry");
    model.component("comp1").physics("mfnc").create("fcal1", "ForceCalculation", 3);
    model.component("comp1").physics("mfnc").feature("fcal1").selection().set(2);
    model.component("comp1").physics("mfnc").feature("fcal1").set("ForceName", "rod");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.0025);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "mfnc.normB");
    model.result("pg1").feature("slc1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickzmethod", "coord");
    model.result("pg1").feature("slc1").set("quickz", 0.005);
    model.result("pg1").feature("slc1").set("colortable", "GrayBody");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"mfnc.Bx", "mfnc.By", "mfnc.Bz"});
    model.result("pg1").feature("arwv1").set("descr", "\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg1").feature("arwv1").set("xnumber", 100);
    model.result("pg1").feature("arwv1").set("ynumber", 50);
    model.result("pg1").feature("arwv1").set("arrowzmethod", "coord");
    model.result("pg1").feature("arwv1").set("zcoord", 0.0051);
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").label("\u5bf9\u79f0\u6761\u4ef6");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").label("\u53cd\u5bf9\u79f0\u6761\u4ef6");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "zx");
    model.result().dataset("mir2").set("vectortrans", "antisymmetric");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir2");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("arwv1").set("expr", new String[]{"mfnc.Bx", "mfnc.By", "mfnc.Bz"});
    model.result("pg1").feature("arwv1").set("descr", "\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"mfnc.Forcex_rod"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7535\u78c1\u529b\uff0cx \u5206\u91cf"});
    model.result().numerical("gev1").set("unit", new String[]{"N"});
    model.result().numerical("gev1").setIndex("expr", "mfnc.Forcex_rod*4", 0);
    model.result().numerical("gev1").setIndex("descr", "\u68d2\u4e0a\u7684\u603b\u529b", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.title("\u6c38\u78c1\u4f53");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u94c1\u68d2\u548c\u6c38\u78c1\u4f53\u4e4b\u95f4\u7684\u9759\u78c1\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("permanent_magnet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
