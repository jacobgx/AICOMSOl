/*
 * pcb_designer_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:58 by COMSOL 6.3.0.290. */
public class pcb_designer_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("BathWidth", "200[mm]", "\u7535\u9540\u69fd\u5bbd\u5ea6\uff08\u6cbf x \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("BathDepth", "100[mm]", "\u7535\u9540\u69fd\u6df1\u5ea6\uff08\u6cbf z \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("BathHeight", "200[mm]", "\u7535\u9540\u69fd\u9ad8\u5ea6\uff08\u6cbf y \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("ApertureWidth", "70[mm]", "\u7f1d\u9699\u5bbd\u5ea6\uff08\u6cbf x \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("ApertureHeight", "70[mm]", "\u7f1d\u9699\u9ad8\u5ea6\uff08\u6cbf y \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("ApertureOffset", "50[mm]", "\u7f1d\u9699\u76f8\u5bf9\u4e8e PCB \u5e03\u5c40\u7684\u504f\u79fb\u91cf\uff08\u6cbf z \u8f74\uff09");
    model.param()
         .set("ApertureThickness", "1[mm]", "\u7f1d\u9699\u539a\u5ea6\uff08\u6cbf z \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("PCBxMin", "7.9925[in]", "\u6240\u5bfc\u5165 PCB \u5e03\u5c40\u8fb9\u6846\u7684\u6700\u5c0f x \u5750\u6807");
    model.param()
         .set("PCBxMax", "11.0075[in]", "\u6240\u5bfc\u5165 PCB \u5e03\u5c40\u8fb9\u6846\u7684\u6700\u5927 x \u5750\u6807");
    model.param()
         .set("PCByMin", "8.9925[in]", "\u6240\u5bfc\u5165 PCB \u5e03\u5c40\u8fb9\u6846\u7684\u6700\u5c0f y \u5750\u6807");
    model.param()
         .set("PCByMax", "11.0075[in]", "\u6240\u5bfc\u5165 PCB \u5e03\u5c40\u8fb9\u6846\u7684\u6700\u5927 y \u5750\u6807");
    model.param()
         .set("PCBWidth", "PCBxMax-PCBxMin", "\u6240\u5bfc\u5165 PCB \u5e03\u5c40\u7684\u5bbd\u5ea6\uff08\u6cbf x \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("PCBHeight", "PCByMax-PCByMin", "\u6240\u5bfc\u5165 PCB \u5e03\u5c40\u7684\u6df1\u5ea6\uff08\u6cbf y \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("PCBThickness", "2[mm]", "PCB \u7535\u4ecb\u8d28\u6750\u6599\u7684\u539a\u5ea6\uff08\u6cbf z \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("PCBOffset", "10[mm]", "PCB \u94dc\u5e03\u5c40\u76f8\u5bf9\u4e8e\u7535\u9540\u69fd\u540e\u58c1\u7684\u504f\u79fb\u91cf\uff08\u6cbf z \u8f74\uff09");
    model.param()
         .set("PCBMargin", "1[mm]", "\u5728\u6240\u5bfc\u5165 PCB \u5e03\u5c40\u5916\u90e8\u7684\u7535\u4ecb\u8d28\u4e0a\u6cbf x \u548c y \u65b9\u5411\u5e94\u7528\u7684\u88d5\u5ea6");
    model.param()
         .set("CopperArea", "0.4262900321[in^2]", "\u6765\u81ea\u5bfc\u5165 PCB \u5e03\u5c40\u7684\u94dc\u5f15\u7ebf\u7684\u9762\u79ef");
    model.param().set("DummyArea", "1.1869299746[in^2]", "\u865a\u62df\u56fe\u6848\u9762\u79ef");
    model.param().set("CathodeArea", "CopperArea+DummyArea", "\u9634\u6781\u9762\u79ef");
    model.param()
         .set("AnodeThickness", "10[mm]", "\u9633\u6781\u539a\u5ea6\uff08\u6cbf z \u8f74\u7684\u5c3a\u5bf8\uff09");
    model.param()
         .set("UseAperture", "1", "\u5982\u679c\u4f7f\u7528\u7f1d\u9699\uff0c\u5219\u8bbe\u4e3a 1\uff0c\u5426\u5219\u8bbe\u4e3a 0\u3002");
    model.param()
         .set("UseDummy", "1", "\u5982\u679c\u4f7f\u7528\u865a\u62df\u56fe\u6848\uff0c\u5219\u8bbe\u4e3a 1\uff0c\u5426\u5219\u8bbe\u4e3a 0\u3002");

    model.component("comp1").geom("geom1").lengthUnit("in");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("PCB");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("size", new String[]{"PCBWidth+2*PCBMargin", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "PCBHeight+2*PCBMargin", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "PCBThickness", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"PCBxMin-PCBMargin", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "PCByMin-PCBMargin", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "PCBOffset-PCBThickness", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "PCBOffset");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("PCB \u94dc\u5e03\u5c40");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").set("filename", "example_pcb.tgz");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("imp1").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "UseDummy");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "PCBOffset");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("PCB \u54d1\u5143\u5e03\u5c40");
    model.component("comp1").geom("geom1").feature("wp2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp1")
         .set("filename", "example_pcb_dummy_pattern.tgz");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("imp1").setIndex("importlayer", false, 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u9634\u6781");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel1", "csel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u7535\u89e3\u69fd");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"BathWidth", "BathHeight", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "BathDepth", 2);
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"PCBxMin-(BathWidth-PCBWidth)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "PCByMin-(BathHeight-PCBHeight)/2", 1);
    model.component("comp1").geom("geom1").feature("blk2").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3")
         .label("\u7535\u89e3\u8d28\u626b\u63a0\u7f51\u683c\u533a\u57df 1");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"BathWidth", "BathHeight", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "PCBThickness", 2);
    model.component("comp1").geom("geom1").feature("blk3")
         .set("pos", new String[]{"PCBxMin-(BathWidth-PCBWidth)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "PCByMin-(BathHeight-PCBHeight)/2", 1);
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "PCBOffset-PCBThickness", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("blk2", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"BathWidth/6", "1"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").setIndex("size", "BathHeight", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"-BathWidth/2+BathWidth/6/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").setIndex("pos", "-BathHeight/2", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("arr1").set("fullsize", new int[]{3, 1});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("arr1")
         .set("displ", new String[]{"BathWidth/3", "0"});
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("ext1").label("\u9633\u6781");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "AnodeThickness", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk2", "blk3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1", "ext1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "UseAperture");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickz", "ApertureOffset+PCBOffset");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cro1").set("intersect", "selected");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("cro1").selection("input").named("blk2");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "ApertureThickness", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("ext2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").label("\u5b54\u5f84\u6e90");
    model.component("comp1").geom("geom1").feature("wp5").set("quickz", "ApertureOffset+PCBOffset");
    model.component("comp1").geom("geom1").feature("wp5").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("size", new String[]{"ApertureWidth", "1"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1").setIndex("size", "ApertureHeight", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("pos", new String[]{"PCBxMin-(BathWidth-PCBWidth)/2+(BathWidth-ApertureWidth)/2", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .setIndex("pos", "PCByMin-(BathHeight-PCBHeight)/2+(BathHeight-ApertureHeight)/2", 1);
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("\u5b54\u5f84");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "ApertureThickness", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("pcb_designer_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
