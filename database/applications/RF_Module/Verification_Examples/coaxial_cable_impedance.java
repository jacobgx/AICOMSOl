/*
 * coaxial_cable_impedance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:44 by COMSOL 6.3.0.290. */
public class coaxial_cable_impedance {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("mode", "ModeAnalysis");
    model.study("std1").feature("mode").set("shiftactive", false);
    model.study("std1").feature("mode").set("linpsolnum", "auto");
    model.study("std1").feature("mode").set("outputmap", new String[]{});
    model.study("std1").feature("mode").set("ngenAUX", "1");
    model.study("std1").feature("mode").set("goalngenAUX", "1");
    model.study("std1").feature("mode").set("ngenAUX", "1");
    model.study("std1").feature("mode").set("goalngenAUX", "1");
    model.study("std1").feature("mode").setSolveFor("/physics/emw", true);

    model.param().set("r_i", "0.5[mm]");
    model.param().descr("r_i", "\u540c\u8f74\u7535\u7f06\u5185\u534a\u5f84");
    model.param().set("r_o", "3.43[mm]");
    model.param().descr("r_o", "\u540c\u8f74\u7535\u7f06\u5916\u534a\u5f84");
    model.param().set("eps_r", "2.4");
    model.param().descr("eps_r", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("Z0_analytic", "(Z0_const/(2*pi*sqrt(eps_r)))*log(r_o/r_i)");
    model.param().descr("Z0_analytic", "\u7279\u6027\u963b\u6297\uff0c\u89e3\u6790");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r_o");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "r_o-r_i", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7edd\u7f18\u4f53");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"eps_r"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_rad");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "int_circ");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(5, 6, 9, 12);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("V", "int_rad(-emw.Ex*t1x-emw.Ey*t1y)");
    model.component("comp1").variable("var1").descr("V", "\u7535\u538b");
    model.component("comp1").variable("var1").set("I", "-int_circ(emw.Hx*t1x+emw.Hy*t1y)");
    model.component("comp1").variable("var1").descr("I", "\u7535\u6d41");
    model.component("comp1").variable("var1").set("L", "4*emw.intWm/(realdot(I,I))/1[m]");
    model.component("comp1").variable("var1").descr("L", "\u7535\u611f");
    model.component("comp1").variable("var1").set("C", "4*emw.intWe/(realdot(V,V))/1[m]");
    model.component("comp1").variable("var1").descr("C", "\u7535\u5bb9");
    model.component("comp1").variable("var1").set("Z0_model", "V/I");
    model.component("comp1").variable("var1").descr("Z0_model", "\u7279\u6027\u963b\u6297");

    model.component("comp1").mesh("mesh1").contribute("physics/emw", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("mode").set("neigsactive", true);
    model.study("std1").feature("mode").set("neigs", 1);
    model.study("std1").feature("mode").set("shiftactive", true);
    model.study("std1").feature("mode").set("shift", "sqrt(eps_r)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("xnumber", 21);
    model.result("pg1").feature("arws1").set("ynumber", 21);
    model.result("pg1").feature("arws1").set("scaleactive", true);
    model.result("pg1").feature("arws1").set("scale", "7e-6");
    model.result("pg1").feature("arws1").set("color", "white");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"tx", "ty"});
    model.result("pg1").feature("arwl1").set("descr", "\u5207\u7ebf");
    model.result("pg1").feature("arwl1").set("arrowcount", 50);
    model.result("pg1").run();
    model.result("pg1").feature("arwl1").active(false);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"Z0_model"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7279\u6027\u963b\u6297"});
    model.result().numerical("gev1").set("unit", new String[]{"\u03a9"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "sqrt(L/C)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.title("\u540c\u8f74\u7535\u7f06\u7684\u963b\u6297");

    model
         .description("\u540c\u8f74\u7535\u7f06\u662f\u4e00\u79cd\u5e94\u7528\u76f8\u5f53\u5e7f\u6cdb\u7684\u4f20\u8f93\u7ebf\u7ed3\u6784\u3002\u672c\u4f8b\u8ba1\u7b97\u540c\u8f74\u7535\u7f06\u4e2d\u7684\u7535\u573a\u548c\u78c1\u573a\u5206\u5e03\uff0c\u4f7f\u7528\u8fd9\u4e24\u4e2a\u573a\u8ba1\u7b97\u4e86\u7535\u7f06\u7684\u7279\u6027\u963b\u6297\uff0c\u5e76\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("coaxial_cable_impedance.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
