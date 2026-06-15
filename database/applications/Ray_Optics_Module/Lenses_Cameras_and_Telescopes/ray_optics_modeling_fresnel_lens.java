/*
 * ray_optics_modeling_fresnel_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:19 by COMSOL 6.3.0.290. */
public class ray_optics_modeling_fresnel_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").geomRep("cadps");

    model.param().set("f0", "5.5[cm]");
    model.param().descr("f0", "\u6709\u6548\u7126\u8ddd");
    model.param().set("n", "1.5");
    model.param().descr("n", "\u6298\u5c04\u7387");
    model.param().set("d", "5[cm]");
    model.param().descr("d", "\u900f\u955c\u76f4\u5f84");
    model.param().set("t_fl", "0.2[cm]");
    model.param().descr("t_fl", "\u8bbe\u8ba1\u7684\u83f2\u6d85\u5c14\u900f\u955c\u539a\u5ea6");
    model.param().set("z_image", "4.7[cm]");
    model.param()
         .descr("z_image", "\u83f2\u6d85\u5c14\u900f\u955c\u5728 z \u65b9\u5411\u7684\u6210\u50cf\u4f4d\u7f6e");
    model.param().set("dx", "10[cm]");
    model.param().descr("dx", "\u5e73\u51f8\u900f\u955c\u5728 x \u65b9\u5411\u7684\u4f4d\u79fb");
    model.param().set("dz", "-0.7[cm]");
    model.param().descr("dz", "\u5e73\u51f8\u900f\u955c\u5728 z \u65b9\u5411\u7684\u4f4d\u79fb");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Spherical_Lenses\\spherical_plano_convex_lens_3d.mph", new String[]{"part4"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "f", "f0");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nref", "n");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Te", "0.1[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d", "d");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "t_fl");
    model.component("comp1").geom("geom1").feature().duplicate("wp2", "wp1");
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "2*t_fl");
    model.component("comp1").geom("geom1").feature().duplicate("wp3", "wp2");
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "3*t_fl");
    model.component("comp1").geom("geom1").feature().duplicate("wp4", "wp3");
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "4*t_fl");
    model.component("comp1").geom("geom1").feature().duplicate("wp5", "wp4");
    model.component("comp1").geom("geom1").feature("wp5").set("quickz", "5*t_fl");
    model.component("comp1").geom("geom1").feature().duplicate("wp6", "wp5");
    model.component("comp1").geom("geom1").feature("wp6").set("quickz", "6*t_fl");
    model.component("comp1").geom("geom1").feature().duplicate("wp7", "wp6");
    model.component("comp1").geom("geom1").feature("wp7").set("quickz", "7*t_fl");
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("par3", "Partition");
    model.component("comp1").geom("geom1").feature("par3").selection("input").set("par2");
    model.component("comp1").geom("geom1").feature("par3").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").run("par3");
    model.component("comp1").geom("geom1").create("par4", "Partition");
    model.component("comp1").geom("geom1").feature("par4").selection("input").set("par3");
    model.component("comp1").geom("geom1").feature("par4").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par4").set("workplane", "wp4");
    model.component("comp1").geom("geom1").run("par4");
    model.component("comp1").geom("geom1").create("par5", "Partition");
    model.component("comp1").geom("geom1").feature("par5").selection("input").set("par4");
    model.component("comp1").geom("geom1").feature("par5").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par5").set("workplane", "wp5");
    model.component("comp1").geom("geom1").run("par5");
    model.component("comp1").geom("geom1").create("par6", "Partition");
    model.component("comp1").geom("geom1").feature("par6").selection("input").set("par5");
    model.component("comp1").geom("geom1").feature("par6").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par6").set("workplane", "wp6");
    model.component("comp1").geom("geom1").run("par6");
    model.component("comp1").geom("geom1").create("par7", "Partition");
    model.component("comp1").geom("geom1").feature("par7").selection("input").set("par6");
    model.component("comp1").geom("geom1").feature("par7").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par7");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("ext1").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("par7", 5);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_fl", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("ext1", 10);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "t_fl", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("ext2", 15);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "t_fl", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext4").selection("inputface").set("ext3", 20);
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "t_fl", 0);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext5").selection("inputface").set("ext4", 25);
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "t_fl", 0);
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext6").selection("inputface").set("ext5", 30);
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "t_fl", 0);
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").feature().create("ext7", "Extrude");
    model.component("comp1").geom("geom1").feature("ext7").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext7").selection("inputface").set("ext6", 36);
    model.component("comp1").geom("geom1").feature("ext7").setIndex("distance", "t_fl", 0);
    model.component("comp1").geom("geom1").run("ext7");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("ext7", 1, 2, 4, 6, 8, 10, 12, 14);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("spl1", "Split");
    model.component("comp1").geom("geom1").feature("spl1").selection("input").set("del1");
    model.component("comp1").geom("geom1").run("spl1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("spl1(6)");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "-t_fl");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mov2", "Move");
    model.component("comp1").geom("geom1").feature("mov2").selection("input").set("spl1(4)");
    model.component("comp1").geom("geom1").feature("mov2").set("displz", "-2*t_fl");
    model.component("comp1").geom("geom1").run("mov2");
    model.component("comp1").geom("geom1").create("mov3", "Move");
    model.component("comp1").geom("geom1").feature("mov3").selection("input").set("spl1(3)");
    model.component("comp1").geom("geom1").feature("mov3").set("displz", "-3*t_fl");
    model.component("comp1").geom("geom1").run("mov3");
    model.component("comp1").geom("geom1").create("mov4", "Move");
    model.component("comp1").geom("geom1").feature("mov4").selection("input").set("spl1(2)");
    model.component("comp1").geom("geom1").feature("mov4").set("displz", "-4*t_fl");
    model.component("comp1").geom("geom1").run("mov4");
    model.component("comp1").geom("geom1").create("mov5", "Move");
    model.component("comp1").geom("geom1").feature("mov5").selection("input").set("spl1(1)");
    model.component("comp1").geom("geom1").feature("mov5").set("displz", "-5*t_fl");
    model.component("comp1").geom("geom1").run("mov5");
    model.component("comp1").geom("geom1").create("mov6", "Move");
    model.component("comp1").geom("geom1").feature("mov6").selection("input").set("spl1(7)");
    model.component("comp1").geom("geom1").feature("mov6").set("displz", "-6*t_fl");
    model.component("comp1").geom("geom1").run("mov6");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "z_image", 2);
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "dx", 0);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").feature().duplicate("pi2", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowbnd", new String[]{"on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"dx", "0", "dz"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n"});

    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("rpt1", "ReleaseFromPoint", 0);
    model.component("comp1").physics("gop").feature("rpt1").selection().set(14, 32);
    model.component("comp1").physics("gop").feature("rpt1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("rpt1").setIndex("Nw", 1000, 0);
    model.component("comp1").physics("gop").feature("rpt1").set("cax", new int[]{0, 0, -1});
    model.component("comp1").physics("gop").feature("rpt1").set("alphac", "pi/8");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Viridis");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("tran1").set("transparency", 0.2);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").run();

    model.title("\u83f2\u6d85\u5c14\u900f\u955c\u7684\u5c04\u7ebf\u8ffd\u8e2a\u4eff\u771f");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5728\u9700\u8981\u77ed\u7126\u8ddd\u65f6\uff0c\u5982\u4f55\u57fa\u4e8e\u4e00\u4e2a\u666e\u901a\u7684\u5e73\u51f8\u900f\u955c\u6765\u6784\u9020\u83f2\u6d85\u5c14\u900f\u955c\u3002\u8fd9\u6837\u6784\u9020\u7684\u83f2\u6d85\u5c14\u900f\u955c\u6bd4\u5e73\u51f8\u900f\u955c\u8f7b\u5f97\u591a\uff0c\u5e76\u4e14\u6210\u672c\u4e5f\u66f4\u4f4e\u3002\u672c\u4f8b\u5c06\u6765\u81ea\u8fd9\u4e24\u79cd\u900f\u955c\u7684\u5149\u7ebf\u7684\u51c6\u76f4\u60c5\u51b5\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("ray_optics_modeling_fresnel_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
