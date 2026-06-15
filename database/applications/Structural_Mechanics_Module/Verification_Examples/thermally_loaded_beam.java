/*
 * thermally_loaded_beam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:26 by COMSOL 6.3.0.290. */
public class thermally_loaded_beam {

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
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/beam", true);

    model.param().set("a", "0.04[m]");
    model.param().descr("a", "\u8fb9\u957f");
    model.param().set("deltaT", "50[K]");
    model.param().descr("deltaT", "\u6e29\u5dee");
    model.param().set("Tg", "deltaT/a");
    model.param().descr("Tg", "\u6e29\u5ea6\u68af\u5ea6");
    model.param().set("Lb", "3[m]");
    model.param().descr("Lb", "\u6881\u957f");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Lb/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "Lb", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 2);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"11e-6"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"210e9"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7800"});

    model.component("comp1").physics("beam").feature("csd1").set("SectionType", "RectangularSection");
    model.component("comp1").physics("beam").feature("csd1").set("hy_rect", "a");
    model.component("comp1").physics("beam").feature("csd1").set("hz_rect", "a");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("OrientationMethod", "vector_beam");
    model.component("comp1").physics("beam").feature("csd1").feature("so1").set("vector_beam", new int[]{0, 1, 0});
    model.component("comp1").physics("beam").create("pdr1", "DispRot0", 0);
    model.component("comp1").physics("beam").feature("pdr1").selection().set(1);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("beam").feature("pdr1").set("RotationType", "RotationGroup");
    model.component("comp1").physics("beam").feature("pdr1").setIndex("FreeRotationAround", true, 1);
    model.component("comp1").physics("beam").feature("pdr1").setIndex("FreeRotationAround", true, 2);
    model.component("comp1").physics("beam").create("pdr2", "DispRot0", 0);
    model.component("comp1").physics("beam").feature("pdr2").selection().set(3);
    model.component("comp1").physics("beam").feature("pdr2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("beam").feature("pdr2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("beam").feature("emm1").create("te1", "ThermalExpansion", 1);

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "0"}});

    model.component("comp1").physics("beam").feature("emm1").feature("te1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("beam").feature("emm1").feature("te1").set("minput_temperature", 200);
    model.component("comp1").physics("beam").feature("emm1").feature("te1").set("TGy", "Tg");
    model.component("comp1").physics("beam").feature("emm1").feature("te1").set("TGz", "-Tg");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1beam", "Beam");
    model.result().dataset("dset1beam").set("data", "dset1");
    model.result().dataset("dset1beam").set("physicsinterface", "beam");
    model.result().dataset("dset1beam").set("refinement", 2);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1beam");
    model.result("pg1").label("\u5e94\u529b\uff0c\u4e09\u7ef4 (beam)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"beam.misesS"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"beam.uS", "beam.vS", "beam.wS"});
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u4f4d\u79fb");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "beam.disp");
    model.result("pg1").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6a2a\u5411\u4f4d\u79fb");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "z \u4f4d\u79fb (mm)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").label("\u6a2a\u5411\u4f4d\u79fb\uff08z \u65b9\u5411\uff09");
    model.result("pg2").feature("lngr1").selection().set(1, 2);
    model.result("pg2").feature("lngr1").set("expr", "w");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb2");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u4f4d\u79fb (mm)");
    model.result("pg3").set("legendpos", "center");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").label("\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").feature("lngr1").selection().set(1, 2);
    model.result("pg3").feature("lngr1").set("descractive", true);
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("linestyle", "cycle");
    model.result("pg3").feature("lngr1").set("linemarker", "cycle");
    model.result("pg3").feature("lngr1").set("markerpos", "interp");
    model.result("pg3").feature("lngr1").set("linewidth", 2);
    model.result("pg3").feature("lngr1").set("autodescr", true);
    model.result("pg3").feature("lngr1").set("autosolution", false);
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").label("\u6a2a\u5411\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").feature("lngr2").set("expr", "sqrt(v^2+w^2)");
    model.result("pg3").feature("lngr2").set("descr", "\u6a2a\u5411\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").feature().duplicate("lngr3", "lngr2");
    model.result("pg3").run();
    model.result("pg3").feature("lngr3").label("\u8f74\u5411\u4f4d\u79fb");
    model.result("pg3").feature("lngr3").set("expr", "u");
    model.result("pg3").feature("lngr3").set("descr", "\u8f74\u5411\u4f4d\u79fb");
    model.result("pg3").run();
    model.result("pg1").run();

    model.title("\u70ed\u8d1f\u8f7d\u6881");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0c\u4e00\u4e2a\u4e09\u7ef4\u6881\u53d7\u5230\u70ed\u8f7d\u8377\u7684\u4f5c\u7528\u3002\u6e29\u5ea6\u6cbf\u6881\u6a2a\u622a\u9762\u5448\u7ebf\u6027\u53d8\u5316\u3002\u5206\u6790\u5f97\u5230\u7684\u89e3\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("thermally_loaded_beam.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
