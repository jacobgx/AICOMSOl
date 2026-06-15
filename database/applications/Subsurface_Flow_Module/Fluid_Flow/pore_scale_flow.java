/*
 * pore_scale_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:29 by COMSOL 6.3.0.290. */
public class pore_scale_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "pore_scale_flow.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.param().set("rho0", "1000[kg/m^3]");
    model.param().descr("rho0", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("eta0", "0.001[kg/(m*s)]");
    model.param().descr("eta0", "\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("p0", "0.715[Pa]");
    model.param().descr("p0", "\u538b\u964d");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"eta0"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2231, 2232);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("p0", "p0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(1, 4, 7, 10, 13, 16);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf").feature("sym1").selection()
         .set(31, 59, 247, 342, 517, 601, 720, 825, 878, 1149, 1300, 1421, 1488, 1748, 1756, 1823, 1912, 2025);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").selection().set(2231, 2232);
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("color", "white");
    model.result("pg1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").physics().create("br", "PorousMediaFlowBrinkman", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/br", false);

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/br", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("k0", "1000[mD]", "\u6e17\u900f\u7387\u5c3a\u5ea6");
    model.param().set("L", "640[um]", "\u57df\u957f\u5ea6");
    model.param().set("H", "320[um]", "\u57df\u9ad8\u5ea6");

    model.component("comp2").geom("geom2").lengthUnit("\u00b5m");
    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp2").geom("geom2").run("r1");

    model.func().create("im1", "Image");
    model.func("im1").set("xmax", "L");
    model.func("im1").set("ymax", "H");
    model.func("im1").set("filename", "pore_scale_flow_structure.png");
    model.func("im1").importData();
    model.func("im1").createPlot("pg2");

    model.result("pg2").run();
    model.result("pg2").label("SEM \u56fe\u50cf");

    model.view("view3").axis().set("viewscaletype", "none");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp2").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"eta0"});
    model.component("comp2").material("mat2").propertyGroup("def").set("porosity", new String[]{"1-0.99*im1(x,y)"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"k0/(100*im1(x,y)+0.1)"});

    model.component("comp2").physics("br").create("sym1", "Symmetry", 1);
    model.component("comp2").physics("br").feature("sym1").selection().set(2, 3);
    model.component("comp2").physics("br").create("inl1", "InletBoundary", 1);
    model.component("comp2").physics("br").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp2").physics("br").feature("inl1").selection().set(4);
    model.component("comp2").physics("br").feature("inl1").set("p0", "p0");
    model.component("comp2").physics("br").create("out1", "OutletBoundary", 1);
    model.component("comp2").physics("br").feature("out1").selection().set(1);

    model.component("comp2").mesh("mesh2").autoMeshSize(2);
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").feature("stat").set("errestandadap", "adaption");
    model.study("std2").feature("stat").set("meshadaptmethod", "modify");
    model.study("std2").feature("stat").set("adapgeom", "geom2");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (br)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").label("\u901f\u5ea6 (br)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("filt1").set("expr", "im1(x,y)<=0.5");
    model.result("pg3").feature("surf1").feature("filt1").set("useder", true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").selection().set(4);
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("color", "white");
    model.result("pg3").run();

    model.title("\u5b54\u9699\u5c3a\u5ea6\u6d41\u52a8");

    model
         .description("\u5728\u8fd9\u4e2a\u521b\u65b0\u793a\u4f8b\u4e2d\uff0c\u7814\u7a76\u4eba\u5458\u901a\u8fc7\u6c42\u89e3\u5355\u4e2a\u5b54\u9699\u5185\u7684\u7eb3\u7ef4-\u65af\u6258\u514b\u65af\u65b9\u7a0b\u6765\u653e\u5927\u5230\u591a\u5b54\u4ecb\u8d28\u6d41\u52a8\uff0c\u5e76\u901a\u8fc7\u626b\u63cf\u5ca9\u77f3\u8584\u7247\u7684\u7535\u5b50\u663e\u5fae\u955c\u56fe\u50cf\u6765\u521b\u5efa\u6a21\u578b\u51e0\u4f55\uff08\u5fae\u7c73\u5c3a\u5ea6\uff09\uff0c\u4e3b\u8981\u7814\u7a76\u95f4\u8d28\u901f\u5ea6\u53d8\u5316\u5bf9\u8fde\u7eed\u6d41\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("pore_scale_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
