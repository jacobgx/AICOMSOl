/*
 * sensitive_high_resolution_ion_microprobe_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:52 by COMSOL 6.3.0.290. */
public class sensitive_high_resolution_ion_microprobe_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("hsample", "0.8[m]", "\u6837\u54c1\u5ba4\u9ad8\u5ea6");
    model.param().set("rsample", "0.25[m]", "\u6837\u54c1\u5ba4\u534a\u5f84");
    model.param().set("hentrance", "1[m]", "\u5165\u53e3\u5ba4\u9ad8\u5ea6");
    model.param().set("rentrance", "0.1[m]", "\u5165\u53e3\u5ba4\u534a\u5f84");
    model.param().set("r0_analyzer", "1272[mm]", "\u7535\u5206\u6790\u4eea\u7684\u5927\u534a\u5f84");
    model.param().set("ang_analyzer", "90[deg]", "\u7535\u5206\u6790\u4eea\u7684\u626b\u63cf\u89d2\u5ea6");
    model.param().set("r1_analyzer", "0.3[m]", "\u7535\u5206\u6790\u4eea\u7684\u5c0f\u534a\u5f84");
    model.param()
         .set("h_analyzer_post", "0.5[m]", "\u7535\u5206\u6790\u4eea\u4e4b\u540e\u7684\u8154\u5ba4\u9ad8\u5ea6");
    model.param()
         .set("h_analyzer_pre", "0.2[m]", "\u7535\u5206\u6790\u4eea\u4e4b\u524d\u7684\u8154\u5ba4\u9ad8\u5ea6");
    model.param().set("hexit", "1[m]", "\u5206\u6790\u4eea\u4e4b\u540e\u7684\u51fa\u53e3\u5ba4\u9ad8\u5ea6");
    model.param().set("rexit", "0.15[m]", "\u5206\u6790\u4eea\u4e4b\u540e\u7684\u51fa\u53e3\u5ba4\u534a\u5f84");
    model.param().set("ang_magnet", "72.5[deg]", "\u78c1\u6027\u8fc7\u6ee4\u5668\u7684\u626b\u63cf\u89d2\u5ea6");
    model.param().set("r_magnet", "1[m]", "\u78c1\u6027\u8fc7\u6ee4\u5668\u7684\u5f2f\u66f2\u534a\u5f84");
    model.param().set("hout", "0.8[m]", "\u78c1\u6027\u8fc7\u6ee4\u5668\u4e4b\u540e\u7684\u8154\u5ba4\u9ad8\u5ea6");
    model.param().set("hdetector", "0.3[m]", "\u68c0\u6d4b\u5668\u5ba4\u9ad8\u5ea6");
    model.param().set("rdetector", "0.25[m]", "\u68c0\u6d4b\u5668\u5ba4\u534a\u5f84");
    model.param().set("rtarget", "1[cm]", "\u76ee\u6807\u534a\u5f84");
    model.param().set("rin", "1[cm]", "\u5165\u53e3\u534a\u5f84");
    model.param().set("hoffset", "0.05[m]", "\u504f\u79fb\u5c3a\u5bf8");

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").label("\u900f\u955c");
    model.geom("part1").inputParam().set("rinner", "0.5[cm]");
    model.geom("part1").inputParam().descr("rinner", "\u5185\u534a\u5f84");
    model.geom("part1").inputParam().set("router", "0.2[m]");
    model.geom("part1").inputParam().descr("router", "\u5916\u534a\u5f84");
    model.geom("part1").inputParam().set("hlens", "0.05[m]");
    model.geom("part1").inputParam().descr("hlens", "\u900f\u955c\u539a\u5ea6");
    model.geom("part1").create("cyl1", "Cylinder");
    model.geom("part1").feature("cyl1").set("r", "router");
    model.geom("part1").feature("cyl1").set("h", "hlens");
    model.geom("part1").feature("cyl1").set("axistype", "y");
    model.geom("part1").run("cyl1");
    model.geom("part1").create("cyl2", "Cylinder");
    model.geom("part1").feature("cyl2").set("r", "rinner");
    model.geom("part1").feature("cyl2").set("h", "hlens");
    model.geom("part1").feature("cyl2").set("axistype", "y");
    model.geom("part1").run("cyl2");
    model.geom("part1").create("dif1", "Difference");
    model.geom("part1").feature("dif1").selection("input").set("cyl1");
    model.geom("part1").feature("dif1").selection("input2").set("cyl2");
    model.geom("part1").run("dif1");
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").feature("wp1").set("planetype", "faceparallel");
    model.geom("part1").feature("wp1").selection("face").set("dif1", 3);
    model.geom("part1").run("wp1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "rsample");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "hsample");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("cyl1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "rin");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "rentrance");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "hentrance");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "hsample", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "r1_analyzer");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "h_analyzer_pre");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "hsample+hentrance", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("cyl3", 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "r1_analyzer");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", "ang_analyzer");
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev1").set("pos3", new String[]{"-r0_analyzer", "0", "0"});
    model.component("comp1").geom("geom1").feature("rev1").setIndex("pos3", "hsample+hentrance+h_analyzer_pre", 1);
    model.component("comp1").geom("geom1").feature("rev1").set("axis3", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("rev1", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "r1_analyzer");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h_analyzer_post", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp4").selection("face").set("ext1", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("c1").set("r", "rexit");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "hexit", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp5").selection("face").set("ext2", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("c1").set("r", "rexit");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", "ang_magnet");
    model.component("comp1").geom("geom1").feature("rev2").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev2")
         .set("pos3", new String[]{"-r0_analyzer-h_analyzer_post-hexit", "0", "0"});
    model.component("comp1").geom("geom1").feature("rev2")
         .setIndex("pos3", "hsample+hentrance+h_analyzer_pre+r0_analyzer-r_magnet", 1);
    model.component("comp1").geom("geom1").feature("rev2").set("axis3", new int[]{0, 0, 1});
    model.component("comp1").geom("geom1").run("rev2");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp6").selection("face").set("rev2", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("c1").set("r", "rexit");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "hout", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp7").selection("face").set("ext3", 1);
    model.component("comp1").geom("geom1").feature("wp7").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("c1").set("r", "rdetector");
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "hdetector", 0);
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("wp8", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp8").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp8").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp8").selection("face").set("ext4", 3);
    model.component("comp1").geom("geom1").feature("wp8").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("c1").set("r", "rtarget");
    model.component("comp1").geom("geom1").run("wp8");
    model.component("comp1").geom("geom1").create("wp9", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp9").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp9").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp9").selection("face").set("cyl1", 4);
    model.component("comp1").geom("geom1").run("wp9");
    model.component("comp1").geom("geom1").create("wp10", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp10").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp10").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp10").selection("face").set("rev1", 1);
    model.component("comp1").geom("geom1").feature("wp10").set("offset", "hoffset");
    model.component("comp1").geom("geom1").run("wp10");
    model.component("comp1").geom("geom1").create("wp11", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp11").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp11").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp11").selection("face").set("ext4", 4);
    model.component("comp1").geom("geom1").feature("wp11").set("offset", "hoffset");
    model.component("comp1").geom("geom1").feature("wp11").set("reverse", true);
    model.component("comp1").geom("geom1").run("wp11");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi1").set("workplane", "wp9");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi3").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi3").set("workplane", "wp10");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi4").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi4").set("workplane", "wp11");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi4");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input")
         .set("cyl1", "cyl2", "cyl3", "ext1", "ext2", "ext3", "ext4", "rev1", "rev2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pi1", "pi2", "pi3", "pi4");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("sensitive_high_resolution_ion_microprobe_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
