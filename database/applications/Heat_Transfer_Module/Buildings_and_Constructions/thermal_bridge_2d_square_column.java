/*
 * thermal_bridge_2d_square_column.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:19 by COMSOL 6.3.0.290. */
public class thermal_bridge_2d_square_column {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Buildings_and_Constructions");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("L", "0.8[m]");
    model.param().descr("L", "\u65b9\u67f1\u8fb9\u957f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L/2", "L"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"1[J/(kg*K)]"});

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "0[degC]");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "0[degC]");
    model.component("comp1").physics("ht").create("temp2", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp2").selection().set(3);
    model.component("comp1").physics("ht").feature("temp2").set("T0", "20[degC]");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("ht").feature("sym1").selection().set(4);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "\u00b0C");
    model.result("pg1").run();
    model.result().export().create("data1", "Data");
    model.result().export("data1").setIndex("unit", "\u00b0C", 0);
    model.result().export("data1").set("filename", "thermal_bridge_2d_square_column.txt");
    model.result().export("data1").set("location", "grid");
    model.result().export("data1").set("gridx2", "range(L/8,L/8,L/2)");
    model.result().export("data1").set("gridy2", "range(L/8,L/8,L-L/8)");
    model.result("pg1").run();

    model.title("\u5efa\u7b51\u7ed3\u6784\u70ed\u6865 - \u65b9\u67f1\u4e8c\u7ef4\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u65b9\u67f1\u4e2d\u7684\u4f20\u70ed\uff0c\u5e76\u5bf9\u8fb9\u754c\u5e94\u7528\u51b7\u70ed\u6e29\u5ea6\u6761\u4ef6\u3002\u7531\u4e8e\u8be5\u95ee\u9898\u5177\u6709\u5bf9\u79f0\u6027\uff0c\u51e0\u4f55\u5f62\u72b6\u53ef\u4ee5\u7b80\u5316\u4e3a\u534a\u4e2a\u6b63\u65b9\u5f62\u3002\u5176\u4e2d\u5c06\u6e29\u5ea6\u5206\u5e03\u4e0e\u5df2\u53d1\u5e03\u7684\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\u3002\u672c\u4f8b\u5bf9\u5e94\u4e8e\u6b27\u6d32\u6807\u51c6 EN ISO 10211:2017 \u4e2d\u63cf\u8ff0\u5efa\u7b51\u7ed3\u6784\u70ed\u6865\u7684\u6848\u4f8b\u00a01\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_bridge_2d_square_column.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
